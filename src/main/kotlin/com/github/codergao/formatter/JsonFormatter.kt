package com.github.codergao.formatter

import com.github.codergao.model.TextFormat
import org.json.JSONArray
import org.json.JSONObject

class JsonFormatter : Formatter {
    override fun format(text: String): String {
        return try {
            val trimmed = text.trim()
            when {
                trimmed.startsWith("[") -> {
                    val arr = JSONArray(trimmed)
                    arr.toString(2)
                }
                trimmed.startsWith("{") -> {
                    val obj = JSONObject(trimmed)
                    obj.toString(2)
                }
                else -> text
            }
        } catch (e: Exception) {
            text
        }
    }

    override fun supports(format: TextFormat): Boolean = format == TextFormat.JSON
}
