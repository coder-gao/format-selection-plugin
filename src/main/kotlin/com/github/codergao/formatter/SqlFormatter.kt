package com.github.codergao.formatter

import com.github.codergao.model.TextFormat

class SqlFormatter : Formatter {
    override fun format(text: String): String {
        return try {
            val keywords = listOf(
                "SELECT", "FROM", "WHERE", "JOIN", "INNER", "LEFT", "RIGHT", "FULL", "OUTER",
                "ON", "GROUP", "BY", "HAVING", "ORDER", "INSERT", "INTO", "VALUES", "UPDATE",
                "SET", "DELETE", "CREATE", "TABLE", "ALTER", "DROP", "PRIMARY", "KEY",
                "FOREIGN", "CONSTRAINT", "AS", "AND", "OR", "NOT", "BETWEEN", "IN", "LIKE",
                "IS", "NULL", "CASE", "WHEN", "THEN", "ELSE", "END", "UNION", "ALL"
            )
            
            var formatted = text
            var indent = 0
            val sb = StringBuilder()
            var i = 0
            
            while (i < formatted.length) {
                val char = formatted[i]
                
                when {
                    char == '(' -> {
                        sb.append(char)
                        indent++
                        i++
                    }
                    char == ')' -> {
                        indent = maxOf(0, indent - 1)
                        sb.append(char)
                        i++
                    }
                    char == ',' -> {
                        sb.append(char)
                        sb.append('\n')
                        sb.append("  ".repeat(indent))
                        i++
                        // Skip whitespace after comma
                        while (i < formatted.length && formatted[i].isWhitespace()) {
                            i++
                        }
                    }
                    char.isWhitespace() -> {
                        // Collect word
                        val wordStart = i
                        while (i < formatted.length && !formatted[i].isWhitespace() && 
                               formatted[i] !in "(),") {
                            i++
                        }
                        val word = formatted.substring(wordStart, i).trim()
                        
                        if (keywords.any { word.equals(it, ignoreCase = true) }) {
                            if (sb.isNotEmpty() && !sb.endsWith('\n')) {
                                sb.append('\n')
                                sb.append("  ".repeat(indent))
                            }
                            sb.append(word.uppercase())
                            sb.append(' ')
                        } else if (word.isNotEmpty()) {
                            sb.append(word)
                            sb.append(' ')
                        }
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

    override fun supports(format: TextFormat): Boolean = format == TextFormat.SQL
}
