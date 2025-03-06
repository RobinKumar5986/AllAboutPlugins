package com.intelij.AllAboutPlugin.PendingCommentInspection

import com.intellij.codeInsight.intention.IntentionAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.*
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.IncorrectOperationException

class ReplacePendingWithTodoIntention : IntentionAction {

    override fun getText(): String = "Replace 'PENDING' with 'TODO'"

    override fun getFamilyName(): String = "Comment Modifications"

    override fun isAvailable(project: Project, editor: Editor?, file: PsiFile?): Boolean {
        if (editor == null || file == null) return false
        val offset = editor.caretModel.offset
        val element = PsiTreeUtil.findElementOfClassAtOffset(file, offset, PsiComment::class.java, false)
        return element != null && element.text.contains("PENDING")
    }

    override fun invoke(project: Project, editor: Editor?, file: PsiFile?) {
        if (editor == null || file == null) return

        val offset = editor.caretModel.offset
        val comment = PsiTreeUtil.findElementOfClassAtOffset(file, offset, PsiComment::class.java, false) ?: return

        try {
            val newText = comment.text.replace("PENDING", "TODO")
            val factory = PsiElementFactory.getInstance(project)
            val newComment = factory.createCommentFromText(newText, file)
            comment.replace(newComment)
        } catch (e: IncorrectOperationException) {
            e.printStackTrace()
        }
    }

    override fun startInWriteAction(): Boolean = true
}