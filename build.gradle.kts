plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.9.21"
    id("org.jetbrains.intellij") version "1.16.1"
}

group = "com.github.codergao"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("org.json:json:20231013")
    testImplementation("junit:junit:4.13.2")
}

intellij {
    version.set("2022.1")
    plugins.set(listOf("com.intellij.java"))
}

tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }
}
