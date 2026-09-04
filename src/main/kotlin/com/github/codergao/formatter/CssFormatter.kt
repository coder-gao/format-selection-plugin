package com.github.codergao.formatter

import com.github.codergao.model.TextFormat

class CssFormatter : Formatter {
    override fun format(text: String): String {
        return try {
            val sb = StringBuilder()
            var indentLevel = 0
            var i = 0
            
            while (i < text.length) {
                val char = text[i]
                
                when {
                    char == '{' -> {
                        sb.append(" {\n")
                        indentLevel++
                        sb.append("  ".repeat(indentLevel))
                        i++
                        // Skip whitespace
                        while (i < text.length && text[i].isWhitespace()) {
                            i++
                        }
                    }
                    char == '}' -> {
                        indentLevel = maxOf(0, indentLevel - 1)
                        // Remove trailing whitespace
                        while (sb.isNotEmpty() && sb.last().isWhitespace()) {
                            sb.deleteCharAt(sb.length - 1)
                        }
                        sb.append("\n}\n\n")
                        i++
                        // Skip whitespace
                        while (i < text.length && text[i].isWhitespace()) {
                            i++
                        }
                    }
                    char == ';' -> {
                        sb.append(char)
                        sb.append('\n')
                        sb.append("  ".repeat(indentLevel))
                        i++
                        // Skip whitespace
                        while (i < text.length && text[i].isWhitespace()) {
                            i++
                        }
                    }
                    char == ',' -> {
                        sb.append(char)
                        sb.append('\n')
                        sb.append("  ".repeat(indentLevel))
                        i++
                        // Skip whitespace
                        while (i < text.length && text[i].isWhitespace()) {
                            i++
                        }
                    }
                    char == '\n' -> {
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

    override fun supports(format: TextFormat): Boolean = format == TextFormat.CSS
}
