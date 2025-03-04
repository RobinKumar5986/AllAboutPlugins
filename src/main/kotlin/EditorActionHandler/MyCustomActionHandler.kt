package com.intelij.AllAboutPlugin.EditorActionHandler

import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.actionSystem.TypedActionHandler

class MyTypedActionHandler(private val originalHandler: TypedActionHandler) : TypedActionHandler {
    override fun execute(editor: Editor, c: Char, dataContext: DataContext) {
        val document = editor.document
        val caretModel = editor.caretModel
        val offset = caretModel.offset

        if (c == '1') { // If user types '1', replace it with 🚀
            document.insertString(offset, "🚀")
            caretModel.moveToOffset(offset + 2) // Move caret forward
        } else {
            originalHandler.execute(editor, c, dataContext) // Let other keys work normally
        }
    }
}
