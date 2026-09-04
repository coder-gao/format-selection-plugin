package com.github.codergao.action

import com.github.codergao.ui.FormatViewerDialog
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.project.DumbAware

class FormatSelectionAction : AnAction(), DumbAware {
    
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project
        val editor = e.getData(CommonDataKeys.EDITOR)
        
        if (project == null || editor == null) {
            return
        }
        
        val selectionModel = editor.selectionModel
        val selectedText = selectionModel.selectedText
        
        if (selectedText.isNullOrEmpty()) {
            return
        }
        
        // Create new dialog for each selection
        val dialog = FormatViewerDialog(project, selectedText)
        dialog.show()
    }
    
    override fun update(e: AnActionEvent) {
        val editor = e.getData(CommonDataKeys.EDITOR)
        val hasSelection = editor?.selectionModel?.hasSelection() ?: false
        e.presentation.isEnabled = hasSelection
    }
}
