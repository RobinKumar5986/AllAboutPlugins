package com.intelij.AllAboutPlugin
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages

class Action2 : AnAction("Action 2") {
    override fun actionPerformed(e: AnActionEvent) {
        Messages.showMessageDialog("Action 2 executed!", "Info", Messages.getInformationIcon())
    }
}