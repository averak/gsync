plugins {
    kotlin("jvm") version libs.versions.kotlin
    kotlin("plugin.allopen") version libs.versions.kotlin

    alias(libs.plugins.versions)
    alias(libs.plugins.version.catalog.update)
    alias(libs.plugins.gradle.git.properties)
    alias(libs.plugins.spotless)
    alias(libs.plugins.sonarqube)
    alias(libs.plugins.quarkus)

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

dependencies {
    // Quarkus
    implementation(enforcedPlatform(libs.quarkus.bom))
    implementation(libs.quarkus.kotlin)
    implementation(libs.quarkus.resteasy.reactive)
    implementation(libs.quarkus.resteasy.reactive.jackson)
    implementation(libs.quarkus.hibernate.orm)
    implementation(libs.quarkus.arc)
    implementation(libs.quarkus.config.yaml)
    implementation(libs.quarkus.logging.json)
    implementation(libs.kotlin.stdlib.jdk8)

    // GCP
    implementation(libs.quarkus.google.cloud.spanner) {
        modules {
            module("com.google.guava:listenablefuture") {
                replacedBy("com.google.guava:guava", "listenablefuture is part of guava")
            }
        }
    }

    // Other utils
    implementation(libs.guava)
    implementation(libs.jackson.module.kotlin)

    // Test Framework & utils
    testImplementation(libs.quarkus.junit5)
    testImplementation(libs.spock.core)
    testImplementation(libs.spock.junit4)
    testImplementation(libs.groovy)
    testImplementation(libs.groovy.sql)
    testImplementation(libs.easy.random)
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
