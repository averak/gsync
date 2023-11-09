plugins {
    kotlin("jvm") version libs.versions.kotlin
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.groovy.sql)
    implementation(libs.easy.random)
    implementation(libs.commons.lang3)
    implementation(libs.guava)
}

kotlin {
    sourceSets {
        all {
            languageSettings {
                languageVersion = "2.0"
            }
        }
    }
}
