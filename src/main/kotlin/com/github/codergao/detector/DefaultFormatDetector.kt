package com.github.codergao.detector

import com.github.codergao.model.TextFormat
import java.util.regex.Pattern

class DefaultFormatDetector : FormatDetector {
    override fun detect(text: String): TextFormat {
        val trimmed = text.trim()
        
        // JSON Detection
        if (isJson(trimmed)) {
            return TextFormat.JSON
        }
        
        // XML/HTML Detection
        if (trimmed.startsWith("<")) {
            return if (isHtml(trimmed)) TextFormat.HTML else TextFormat.XML
        }
        
        // SQL Detection
        if (isSql(trimmed)) {
            return TextFormat.SQL
        }
        
        // YAML Detection
        if (isYaml(trimmed)) {
            return TextFormat.YAML
        }
        
        // JavaScript/TypeScript Detection
        if (isJavaScript(trimmed)) {
            return TextFormat.JAVASCRIPT
        }
        
        // CSS Detection
        if (isCss(trimmed)) {
            return TextFormat.CSS
        }
        
        // Properties Detection
        if (isProperties(trimmed)) {
            return TextFormat.PROPERTIES
        }
        
        return TextFormat.PLAIN_TEXT
    }
    
    private fun isJson(text: String): Boolean {
        return try {
            val trimmed = text.trim()
            (trimmed.startsWith("{") && trimmed.endsWith("}")) ||
            (trimmed.startsWith("[") && trimmed.endsWith("]"))
        } catch (e: Exception) {
            false
        }
    }
    
    private fun isHtml(text: String): Boolean {
        val htmlTags = setOf("html", "head", "body", "div", "p", "span", "a", "img", "script")
        return htmlTags.any { tag ->
            text.contains("<$tag", ignoreCase = true) || text.contains("</$tag>", ignoreCase = true)
        }
    }
    
    private fun isSql(text: String): Boolean {
        val sqlKeywords = setOf("SELECT", "INSERT", "UPDATE", "DELETE", "CREATE", "ALTER", "DROP", "WHERE", "JOIN", "GROUP BY")
        return sqlKeywords.any { keyword -> text.contains(keyword, ignoreCase = true) }
    }
    
    private fun isYaml(text: String): Boolean {
        val yamlPattern = Pattern.compile("^[a-zA-Z_][a-zA-Z0-9_]*\\s*:", Pattern.MULTILINE)
        return yamlPattern.matcher(text).find()
    }
    
    private fun isJavaScript(text: String): Boolean {
        val jsKeywords = setOf("function", "const", "let", "var", "=>")
        return jsKeywords.any { text.contains(it) }
    }
    
    private fun isCss(text: String): Boolean {
        return text.contains("{") && text.contains("}") && 
               (text.contains(":") && text.contains(";"))
    }
    
    private fun isProperties(text: String): Boolean {
        return text.contains("=") && !text.contains("{") && !text.contains("[")
    }
}
