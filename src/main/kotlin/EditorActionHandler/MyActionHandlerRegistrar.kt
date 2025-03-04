package com.intelij.AllAboutPlugin.EditorActionHandler

import com.intellij.openapi.startup.StartupActivity
import com.intellij.openapi.editor.actionSystem.TypedAction
import com.intellij.openapi.project.Project

class MyStartupActivity : StartupActivity {
    override fun runActivity(project: Project) {
        val typedAction = TypedAction.getInstance()
        val originalHandler = typedAction.handler // ✅ Use `handler` instead of `rawHandler`
        typedAction.setupHandler(MyTypedActionHandler(originalHandler)) // ✅ Set custom handler
    }
}
