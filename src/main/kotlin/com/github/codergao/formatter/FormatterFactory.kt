package com.github.codergao.formatter

import com.github.codergao.model.TextFormat

object FormatterFactory {
    private val formatters = listOf(
        JsonFormatter(),
        XmlFormatter(),
        HtmlFormatter(),
        SqlFormatter(),
        JavaScriptFormatter(),
        CssFormatter()
    )
    
    fun getFormatter(format: TextFormat): Formatter? {
        return formatters.firstOrNull { it.supports(format) }
    }
    
    fun getAllFormatters(): List<Formatter> = formatters
}
