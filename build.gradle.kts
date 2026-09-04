plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.8.22"
    id("org.jetbrains.intellij") version "1.15.0"
}

group = "com.github.codergao"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.code.gson:gson:2.8.9")
    implementation("org.json:json:20230227")
}

intellij {
    version.set("2023.1")
    plugins.set(listOf())
}

tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "11"
            apiVersion = "1.8"
            languageVersion = "1.8"
        }
    }
    
    patchPluginXml {
        changeNotes.set("Initial release")
    }
}
