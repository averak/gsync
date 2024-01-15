import java.io.ByteArrayOutputStream

plugins {
    kotlin("jvm") version "1.9.20"

    alias(libs.plugins.springboot)
    alias(libs.plugins.versions)
    alias(libs.plugins.version.catalog.update)
    alias(libs.plugins.flyway)
    alias(libs.plugins.gradle.git.properties)
    alias(libs.plugins.spotless)
    alias(libs.plugins.sonarqube)

    groovy
    jacoco
}

buildscript {
    dependencies {
        classpath(libs.spring.boot.gradle.plugin)
        classpath(libs.flyway.gradle.plugin)
        classpath(libs.flyway.spanner)
        classpath(libs.google.cloud.spanner.jdbc)
    }
}

allprojects {
    group = "net.averak.gsync"

    repositories {
        mavenCentral()
        gradlePluginPortal()
    }

    apply {
        plugin("java")
        plugin("kotlin")
        plugin("groovy")
        plugin("jacoco")
        plugin(rootProject.libs.plugins.spotless.get().pluginId)
        plugin(rootProject.libs.plugins.sonarqube.get().pluginId)
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        jvmToolchain {
            languageVersion = JavaLanguageVersion.of(17)
        }

        sourceSets {
            all {
                languageSettings {
                    languageVersion = "2.0"
                }
            }
        }
    }

    spotless {
        kotlin {
            targetExclude("build/**")
            ktlint()
                .setEditorConfigPath("$rootDir/.editorconfig")
                .editorConfigOverride(
                    mapOf(
                        // .editorconfig のルール無効化設定を読み込んでくれないので、再度設定する必要がある
                        "ktlint_standard_no-wildcard-imports" to "disabled",
                        "ktlint_standard_package-name" to "disabled",
                        "ktlint_standard_max-line-length" to "disabled",
                    ),
                )
        }

        java {
            targetExclude("build/**")
            eclipse()
        }

        groovy {
            targetExclude("build/**")
        }
    }

    sonar {
        properties {
            property("sonar.projectKey", "averak_gsync")
            property("sonar.organization", "averak")
            property("sonar.host.url", "https://sonarcloud.io")
            property("sonar.exclusions", "protobuf/**,testkit/**,**/dto/**,**/mapper/base/**")
        }
    }

    tasks {
        test {
            useJUnitPlatform()
        }

        javadoc {
            (options as StandardJavadocDocletOptions).addBooleanOption("Xdoclint:none", true)
        }

        processResources {
            dependsOn(":generateGitProperties")
        }

        processTestResources {
            dependsOn(":generateGitProperties")
        }

        jacocoTestReport {
            reports {
                xml.required = true
                csv.required = true
            }
        }
    }
}

subprojects {
    sourceSets {
        test {
            resources.srcDir("$rootDir/src/test/resources")
        }
    }

    dependencies {
        implementation(rootProject.libs.spring.boot.starter)
        implementation(rootProject.libs.guava)
        testImplementation(project(":testkit"))
    }
}

project(":adapter") {
    dependencies {
        implementation(project(":core"))
        implementation(project(":domain"))
        implementation(project(":infrastructure"))
        implementation(project(":usecase"))
        implementation(project(":protobuf"))
        implementation(rootProject.libs.spring.boot.starter.web)
        implementation(rootProject.libs.spring.boot.starter.webflux)
        implementation(rootProject.libs.spring.boot.starter.data.jpa)
        implementation(rootProject.libs.mybatis.spring.boot.starter)
    }
}

project(":core") {
    dependencies {
        implementation(rootProject.libs.logback.classic)
        implementation(rootProject.libs.logstash.logback.encoder)
    }
}

project(":domain") {
    dependencies {
        implementation(project(":core"))
        implementation(rootProject.libs.spring.boot.starter)
    }
}

project(":infrastructure") {
    dependencies {
        implementation(project(":core"))
        implementation(project(":protobuf"))
        implementation(rootProject.libs.spring.boot.starter.web)
        implementation(rootProject.libs.spring.boot.starter.webflux)
        implementation(rootProject.libs.spring.boot.starter.data.redis)
        implementation(rootProject.libs.jackson.module.kotlin)
        implementation(rootProject.libs.jackson.datatype.jsr310)
        implementation(rootProject.libs.mybatis.spring.boot.starter)
        implementation(rootProject.libs.mybatis.generator.maven.plugin)
    }
}

project(":usecase") {
    dependencies {
        implementation(project(":core"))
        implementation(project(":domain"))
    }
}

project(":testkit") {
    dependencies {
        implementation(rootProject)
        implementation(project(":core"))
        implementation(project(":adapter"))
        implementation(project(":domain"))
        implementation(project(":infrastructure"))
        implementation(project(":usecase"))
        implementation(rootProject.libs.spring.boot.starter.test)
        implementation(rootProject.libs.spring.boot.starter.web)
        implementation(rootProject.libs.spring.boot.starter.webflux)
        implementation(rootProject.libs.spring.boot.starter.data.jpa)
        implementation(rootProject.libs.spring.boot.starter.data.redis)
        implementation(rootProject.libs.commons.lang3)

        api(project(":protobuf"))
        api(rootProject.libs.spock.core)
        api(rootProject.libs.spock.spring)
        api(rootProject.libs.groovy.sql)
        api(rootProject.libs.easy.random)
    }

    tasks {
        compileGroovy {
            dependsOn("compileKotlin")
            classpath += files("build/classes/kotlin/main")
        }
    }
}

project(":protobuf") {
    dependencies {
        compileOnly(rootProject.libs.javax.annotation.api)
        api(rootProject.libs.io.grpc.netty)
        api(rootProject.libs.io.grpc.netty.shaded)
        api(rootProject.libs.io.grpc.protobuf)
        api(rootProject.libs.io.grpc.services)
        api(rootProject.libs.io.grpc.stub)
    }
}

dependencies {
    implementation(project(":adapter"))
    implementation(project(":core"))
    implementation(project(":domain"))
    implementation(project(":infrastructure"))
    implementation(project(":usecase"))
    implementation(libs.spring.boot.starter)
    implementation(libs.spring.boot.starter.data.jpa)
    implementation(libs.google.cloud.spanner.jdbc)
    implementation(libs.flyway.core)
    implementation(libs.flyway.spanner)
}

flyway {
    url = "jdbc:cloudspanner://localhost:9010/projects/gsync-sandbox/instances/sandbox/databases/sandbox?autoConfigEmulator=true"
    cleanDisabled = false
}

gitProperties {
    val stdout = ByteArrayOutputStream()
    project.exec {
        commandLine("git", "describe", "--tags", "--abbrev=1")
        standardOutput = stdout
        isIgnoreExitValue = true
    }
    val version = stdout.toString().trim().replaceFirst("^v", "")
    customProperty("git.commit.id.describe", version)
    gitPropertiesResourceDir = file("$rootDir/build/git/src/main/resources")
}

tasks {
    val mybatisGenerator: Configuration by configurations.creating
    dependencies {
        mybatisGenerator(project(":infrastructure"))
        mybatisGenerator(libs.mybatis.generator.core)
        mybatisGenerator(libs.google.cloud.spanner.jdbc)
    }
    register("mbgenerate", Task::class) {
        doFirst {
            fileTree("$rootDir/app/adapter/src/main/java/net/averak/gsync/adapter/dao/dto/base") {
                include("**/*.java")
                exclude("**/AbstractDto.java")
            }.files.forEach { it.delete() }
            fileTree("$rootDir/app/adapter/src/main/java/net/averak/gsync/adapter/dao/mapper/base") {
                include("**/*.java")
            }.files.forEach { it.delete() }
            fileTree("$rootDir/src/main/resources/dao/base") {
                include("**/*.xml")
            }.files.forEach { it.delete() }
        }
        doLast {
            ant.withGroovyBuilder {
                "taskdef"(
                    "name" to "mbgenerator",
                    "classname" to "org.mybatis.generator.ant.GeneratorAntTask",
                    "classpath" to mybatisGenerator.asPath,
                )
            }
            ant.withGroovyBuilder {
                "mbgenerator"(
                    "overwrite" to true,
                    "configfile" to "$rootDir/src/main/resources/mybatis-generator-config.xml",
                    "verbose" to true,
                )
            }
        }
    }
}
