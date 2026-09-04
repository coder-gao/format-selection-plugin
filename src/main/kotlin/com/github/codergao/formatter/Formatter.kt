package com.github.codergao.formatter

import com.github.codergao.model.TextFormat

interface Formatter {
    fun format(text: String): String
    fun supports(format: TextFormat): Boolean
}
