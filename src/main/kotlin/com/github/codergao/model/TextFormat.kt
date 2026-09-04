package com.github.codergao.model

enum class TextFormat(val displayName: String, val mimeType: String) {
    JSON("JSON", "application/json"),
    XML("XML", "application/xml"),
    HTML("HTML", "text/html"),
    SQL("SQL", "text/sql"),
    JAVASCRIPT("JavaScript", "text/javascript"),
    TYPESCRIPT("TypeScript", "text/typescript"),
    CSS("CSS", "text/css"),
    YAML("YAML", "text/yaml"),
    PYTHON("Python", "text/python"),
    JAVA("Java", "text/java"),
    KOTLIN("Kotlin", "text/kotlin"),
    PROPERTIES("Properties", "text/properties"),
    PLAIN_TEXT("Plain Text", "text/plain");

    companion object {
        fun fromMimeType(mimeType: String?): TextFormat {
            return values().firstOrNull { it.mimeType == mimeType } ?: PLAIN_TEXT
        }
    }
}
