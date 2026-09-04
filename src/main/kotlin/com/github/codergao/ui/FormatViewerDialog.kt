package com.github.codergao.ui

import com.github.codergao.detector.DefaultFormatDetector
import com.github.codergao.formatter.FormatterFactory
import com.github.codergao.model.TextFormat
import com.intellij.openapi.editor.EditorFactory
import com.intellij.openapi.editor.ex.EditorEx
import com.intellij.openapi.fileTypes.FileTypeManager
import com.intellij.openapi.fileTypes.FileTypes
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import java.awt.BorderLayout
import javax.swing.JComboBox
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JPanel

class FormatViewerDialog(
    private val project: Project,
    private val selectedText: String
) : DialogWrapper(project, true) {
    private val detector = DefaultFormatDetector()
    private var detectedFormat = detector.detect(selectedText)
    private lateinit var formatComboBox: JComboBox<TextFormat>
    private lateinit var editorPanel: JPanel
    private var currentEditor: EditorEx? = null
    
    init {
        title = "Format Selection Viewer"
        isModal = false
        isResizable = true
        init()
    }
    
    override fun createCenterPanel(): JComponent? {
        val mainPanel = JPanel(BorderLayout())
        
        // Top panel with controls
        val topPanel = JPanel(BorderLayout())
        
        // Format selector
        formatComboBox = JComboBox(TextFormat.values())
        formatComboBox.selectedItem = detectedFormat
        formatComboBox.addActionListener { updateFormat() }
        
        val label = JLabel("Format: ")
        topPanel.add(label, BorderLayout.WEST)
        topPanel.add(formatComboBox, BorderLayout.CENTER)
        
        // Center panel with editor
        editorPanel = JPanel(BorderLayout())
        updateEditor()
        
        mainPanel.add(topPanel, BorderLayout.NORTH)
        mainPanel.add(editorPanel, BorderLayout.CENTER)
        
        return mainPanel
    }
    
    private fun updateFormat() {
        val selectedFormat = formatComboBox.selectedItem as? TextFormat ?: return
        detectedFormat = selectedFormat
        updateEditor()
    }
    
    private fun updateEditor() {
        editorPanel.removeAll()
        
        val formattedText = try {
            val formatter = FormatterFactory.getFormatter(detectedFormat)
            if (formatter != null) {
                formatter.format(selectedText)
            } else {
                selectedText
            }
        } catch (e: Exception) {
            selectedText
        }
        
        // Create editor
        val document = EditorFactory.getInstance().createDocument(formattedText)
        val fileType = when (detectedFormat) {
            TextFormat.JSON -> FileTypeManager.getInstance().getFileTypeByExtension("json") ?: FileTypes.PLAIN_TEXT
            TextFormat.XML -> FileTypeManager.getInstance().getFileTypeByExtension("xml") ?: FileTypes.PLAIN_TEXT
            TextFormat.HTML -> FileTypeManager.getInstance().getFileTypeByExtension("html") ?: FileTypes.PLAIN_TEXT
            TextFormat.SQL -> FileTypeManager.getInstance().getFileTypeByExtension("sql") ?: FileTypes.PLAIN_TEXT
            TextFormat.JAVASCRIPT -> FileTypeManager.getInstance().getFileTypeByExtension("js") ?: FileTypes.PLAIN_TEXT
            TextFormat.TYPESCRIPT -> FileTypeManager.getInstance().getFileTypeByExtension("ts") ?: FileTypes.PLAIN_TEXT
            TextFormat.CSS -> FileTypeManager.getInstance().getFileTypeByExtension("css") ?: FileTypes.PLAIN_TEXT
            TextFormat.YAML -> FileTypeManager.getInstance().getFileTypeByExtension("yaml") ?: FileTypes.PLAIN_TEXT
            TextFormat.PYTHON -> FileTypeManager.getInstance().getFileTypeByExtension("py") ?: FileTypes.PLAIN_TEXT
            TextFormat.JAVA -> FileTypeManager.getInstance().getFileTypeByExtension("java") ?: FileTypes.PLAIN_TEXT
            TextFormat.KOTLIN -> FileTypeManager.getInstance().getFileTypeByExtension("kt") ?: FileTypes.PLAIN_TEXT
            TextFormat.PROPERTIES -> FileTypeManager.getInstance().getFileTypeByExtension("properties") ?: FileTypes.PLAIN_TEXT
            else -> FileTypes.PLAIN_TEXT
        }
        
        val editor = EditorFactory.getInstance().createEditor(document, project, fileType, true) as EditorEx
        editor.isViewer = true
        
        editorPanel.add(editor.component, BorderLayout.CENTER)
        editorPanel.revalidate()
        editorPanel.repaint()
        
        // Store for cleanup
        currentEditor = editor
    }
    
    override fun createActions() = arrayOf()
    
    override fun getPreferredFocusedComponent(): JComponent? = formatComboBox
    
    override fun dispose() {
        if (currentEditor != null) {
            EditorFactory.getInstance().releaseEditor(currentEditor!!)
        }
        super.dispose()
    }
}
