package com.github.codergao.formatter

import com.github.codergao.model.TextFormat

class HtmlFormatter : Formatter {
    override fun format(text: String): String {
        return try {
            val sb = StringBuilder()
            var indentLevel = 0
            var i = 0
            
            while (i < text.length) {
                when {
                    text[i] == '<' -> {
                        if (i > 0 && text[i - 1] != '\n') {
                            sb.append('\n')
                        }
                        
                        val tagEnd = text.indexOf('>', i)
                        if (tagEnd != -1) {
                            val tag = text.substring(i, tagEnd + 1)
                            
                            if (tag.startsWith("</")) {
                                indentLevel = maxOf(0, indentLevel - 1)
                                sb.append("  ".repeat(indentLevel))
                            } else if (!tag.endsWith("/>") && !isSelfClosingTag(tag)) {
                                sb.append("  ".repeat(indentLevel))
                                if (!tag.startsWith("<!") && !tag.startsWith("<?")) {
                                    indentLevel++
                                }
                            } else {
                                sb.append("  ".repeat(indentLevel))
                            }
                            
                            sb.append(tag)
                            i = tagEnd + 1
                        } else {
                            sb.append(text[i])
                            i++
                        }
                    }
                    text[i].isWhitespace() -> {
                        i++
                    }
                    else -> {
                        val contentEnd = text.indexOf('<', i)
                        val content = if (contentEnd == -1) text.substring(i) else text.substring(i, contentEnd)
                        val trimmed = content.trim()
                        if (trimmed.isNotEmpty()) {
                            sb.append("  ".repeat(indentLevel))
                            sb.append(trimmed)
                            sb.append('\n')
                        }
                        i = if (contentEnd == -1) text.length else contentEnd
                    }
                }
            }
            
            sb.toString().trim()
        } catch (e: Exception) {
            text
        }
    }
    
    private fun isSelfClosingTag(tag: String): Boolean {
        val selfClosing = setOf("img", "br", "hr", "input", "meta", "link")
        return selfClosing.any { tag.contains(it, ignoreCase = true) }
    }

    override fun supports(format: TextFormat): Boolean = format == TextFormat.HTML
}
