package com.intelij.AllAboutPlugin
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages

class Action1: AnAction("Action 1") {
    override fun actionPerformed(p0: AnActionEvent) {
        Messages.showMessageDialog("Action 1 executed!", "Info", Messages.getInformationIcon())
    }
}