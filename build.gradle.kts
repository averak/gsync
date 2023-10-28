plugins {
    kotlin("jvm") version "1.9.10"
    kotlin("plugin.allopen") version "1.9.10"

    id("io.quarkus")
    id("com.github.ben-manes.versions") version "0.47.0"
    id("com.diffplug.spotless") version "6.21.0"
    id("org.sonarqube") version "4.4.1.3373"

    application
    java
    groovy
    jacoco
    eclipse
    idea
}

buildscript {
    dependencies {
        classpath("com.github.ben-manes:gradle-versions-plugin:0.47.0")
    }
}

repositories {
    mavenCentral()
    mavenLocal()
    gradlePluginPortal()
}

group = "net.averak.gsync"
version = "1.0.0-SNAPSHOT"

val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project

dependencies {
    // Quarkus
    implementation(enforcedPlatform("$quarkusPlatformGroupId:$quarkusPlatformArtifactId:$quarkusPlatformVersion"))
    implementation("io.quarkus:quarkus-kotlin")
    implementation("io.quarkus:quarkus-resteasy-reactive-jackson")
    implementation("io.quarkus:quarkus-hibernate-orm")
    implementation("io.quarkus:quarkus-arc")
    implementation("io.quarkus:quarkus-resteasy-reactive")
    implementation("io.quarkus:quarkus-config-yaml")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // GCP
    // implementation("io.quarkiverse.googlecloudservices:quarkus-google-cloud-parent:2.5.0")
    // implementation("com.google.cloud:google-cloud-spanner:6.51.0")
    implementation("io.quarkiverse.googlecloudservices:quarkus-google-cloud-spanner:2.5.0") {
        modules {
            module("com.google.guava:listenablefuture") {
                replacedBy("com.google.guava:guava", "listenablefuture is part of guava")
            }
        }
    }

    // utils
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.2")
    implementation("com.google.guava:guava:32.1.3-jre")

    // test
    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.github.dvgaba:easy-random-core:6.2.0")
    testImplementation("org.apache.groovy:groovy")
    testImplementation("org.apache.groovy:groovy-sql")
    testImplementation("org.spockframework:spock-core:2.4-M1-groovy-4.0")
    testImplementation("org.spockframework:spock-junit4:2.4-M1-groovy-4.0")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    jvmToolchain(17)
}

allOpen {
    annotation("jakarta.ws.rs.Path")
    annotation("jakarta.enterprise.context.ApplicationScoped")
    annotation("io.quarkus.test.junit.QuarkusTest")
}

spotless {
    kotlin {
        targetExclude("build/**/*.kt")
        ktlint()
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
    }
}

sonar {
    properties {
        property("sonar.projectKey", "averak_gsync")
        property("sonar.organization", "averak")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}

tasks {
    test {
        useJUnitPlatform()
    }

    jar {
        enabled = false
    }

    javadoc {
        (options as StandardJavadocDocletOptions).addBooleanOption("Xdoclint:none", true)
    }

    jacocoTestReport {
        reports {
            xml.required = true
            csv.required = true
        }
    }
}
