plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "2.0.0"
    id("org.jetbrains.intellij") version "1.17.3"
}

group = "com.github.codergao"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("org.json:json:20231013")
}

intellij {
    version.set("2025.1")
    plugins.set(listOf())
}

tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "17"
        }
    }

    patchPluginXml {
        changeNotes.set("Initial release")
    }
}
