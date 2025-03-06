package com.intelij.AllAboutPlugin.PendingCommentInspection

import com.intellij.codeInspection.LocalInspectionTool
import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.PsiComment
import com.intellij.psi.PsiElementVisitor
import com.intellij.psi.PsiRecursiveElementVisitor

class PendingCommentInspection : LocalInspectionTool() {

    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor {
        return object : PsiRecursiveElementVisitor() {
            override fun visitComment(comment: PsiComment) {
                super.visitComment(comment)
                if (comment.text.contains("PENDING")) {
                    holder.registerProblem(
                        comment,
                        "PENDING comment found",
                        ProblemHighlightType.WARNING
                    )
                }
            }
        }
    }
}
