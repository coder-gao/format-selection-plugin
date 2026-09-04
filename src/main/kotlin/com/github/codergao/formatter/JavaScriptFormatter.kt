package com.github.codergao.formatter

import com.github.codergao.model.TextFormat

class JavaScriptFormatter : Formatter {
    override fun format(text: String): String {
        return try {
            val sb = StringBuilder()
            var indentLevel = 0
            var i = 0
            var inString = false
            var stringChar = ' '
            
            while (i < text.length) {
                val char = text[i]
                
                when {
                    !inString && (char == '"' || char == '\'' || char == '`') -> {
                        inString = true
                        stringChar = char
                        sb.append(char)
                        i++
                    }
                    inString && char == stringChar && (i == 0 || text[i - 1] != '\\') -> {
                        inString = false
                        sb.append(char)
                        i++
                    }
                    !inString && (char == '{' || char == '[') -> {
                        sb.append(char)
                        if (i + 1 < text.length && text[i + 1] != '\n') {
                            sb.append('\n')
                        }
                        indentLevel++
                        sb.append("  ".repeat(indentLevel))
                        i++
                        // Skip whitespace
                        while (i < text.length && text[i].isWhitespace()) {
                            i++
                        }
                    }
                    !inString && (char == '}' || char == ']') -> {
                        indentLevel = maxOf(0, indentLevel - 1)
                        // Remove trailing whitespace
                        while (sb.isNotEmpty() && sb.last().isWhitespace()) {
                            sb.deleteCharAt(sb.length - 1)
                        }
                        sb.append('\n')
                        sb.append("  ".repeat(indentLevel))
                        sb.append(char)
                        i++
                    }
                    !inString && char == ',' -> {
                        sb.append(char)
                        if (i + 1 < text.length && text[i + 1] != '\n') {
                            sb.append('\n')
                            sb.append("  ".repeat(indentLevel))
                        }
                        i++
                        // Skip whitespace
                        while (i < text.length && text[i].isWhitespace()) {
                            i++
                        }
                    }
                    !inString && char == ';' -> {
                        sb.append(char)
                        if (i + 1 < text.length && text[i + 1] != '\n') {
                            sb.append('\n')
                            sb.append("  ".repeat(indentLevel))
                        }
                        i++
                        // Skip whitespace
                        while (i < text.length && text[i].isWhitespace()) {
                            i++
                        }
                    }
                    char == '\n' -> {
                        if (sb.isNotEmpty() && !sb.endsWith('\n')) {
                            sb.append('\n')
                        }
                        i++
                    }
                    else -> {
                        sb.append(char)
                        i++
                    }
                }
            }
            
            sb.toString().trim()
        } catch (e: Exception) {
            text
        }
    }

    override fun supports(format: TextFormat): Boolean = format == TextFormat.JAVASCRIPT || format == TextFormat.TYPESCRIPT
}
