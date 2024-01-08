import java.io.ByteArrayOutputStream

plugins {
    kotlin("jvm") version libs.versions.kotlin

    alias(libs.plugins.versions)
    alias(libs.plugins.version.catalog.update)
    alias(libs.plugins.gradle.git.properties)
    alias(libs.plugins.spotless)
    alias(libs.plugins.sonarqube)

    groovy
    jacoco
}

buildscript {
    dependencies {
        classpath(libs.spring.boot.gradle.plugin)
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

        groovy {
            targetExclude("build/**")
        }
    }

    sonar {
        properties {
            property("sonar.projectKey", "averak_gsync")
            property("sonar.organization", "averak")
            property("sonar.host.url", "https://sonarcloud.io")
            property("sonar.exclusions", "testkit/**")
        }
    }

    tasks {
        test {
            useJUnitPlatform()
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
        implementation(rootProject.libs.spring.boot.starter.web)
        implementation(rootProject.libs.spring.boot.starter.webflux)

        testImplementation(rootProject.libs.spring.boot.starter.test)
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
        implementation(rootProject.libs.spring.boot.starter.web)
        implementation(rootProject.libs.spring.boot.starter.webflux)
        implementation(rootProject.libs.jackson.module.kotlin)
        implementation(rootProject.libs.jackson.datatype.jsr310)
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
        implementation(rootProject.libs.spring.boot.starter.data.jpa)
        implementation(rootProject.libs.spring.boot.starter.data.redis)
        implementation(rootProject.libs.commons.lang3)

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

dependencies {
    implementation(project(":adapter"))
    implementation(project(":core"))
    implementation(project(":domain"))
    implementation(project(":infrastructure"))
    implementation(project(":usecase"))
    implementation(libs.spring.boot.starter)
    implementation(libs.google.cloud.spanner.spring)
    implementation(libs.google.cloud.spanner.jdbc)
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
