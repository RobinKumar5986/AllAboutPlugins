package com.intelij.AllAboutPlugin.networking

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.DialogWrapper
import javax.swing.JComponent

class ApiAction : AnAction("API") {
    override fun actionPerformed(e: AnActionEvent) {
        val dialog = ApiDialog()
        dialog.show()
    }
}