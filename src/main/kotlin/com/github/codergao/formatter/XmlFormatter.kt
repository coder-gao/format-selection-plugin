package com.github.codergao.formatter

import com.github.codergao.model.TextFormat
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult
import java.io.StringWriter

class XmlFormatter : Formatter {
    override fun format(text: String): String {
        return try {
            val dbFactory = DocumentBuilderFactory.newInstance()
            dbFactory.isNamespaceAware = true
            val dBuilder = dbFactory.newDocumentBuilder()
            val doc = dBuilder.parse(text.byteInputStream())
            
            val transformer = TransformerFactory.newInstance().newTransformer()
            transformer.setOutputProperty(OutputKeys.INDENT, "yes")
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2")
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8")
            
            val stringWriter = StringWriter()
            transformer.transform(DOMSource(doc), StreamResult(stringWriter))
            stringWriter.toString()
        } catch (e: Exception) {
            text
        }
    }

    override fun supports(format: TextFormat): Boolean = format == TextFormat.XML
}
