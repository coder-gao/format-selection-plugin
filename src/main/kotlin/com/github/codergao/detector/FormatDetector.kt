package com.github.codergao.detector

import com.github.codergao.model.TextFormat

interface FormatDetector {
    fun detect(text: String): TextFormat
}
