package com.intelij.AllAboutPlugin.CodeInspection

import com.intellij.codeInspection.*
import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications
import com.intellij.psi.PsiElementVisitor
import org.jetbrains.kotlin.idea.codeinsight.api.classic.inspections.AbstractKotlinInspection
import org.jetbrains.kotlin.psi.*

class MyKotlinInspection : AbstractKotlinInspection() {
    companion object {
        private const val MAX_LINES = 5
    }

    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor {
        return object : KtVisitorVoid() {
            override fun visitNamedFunction(function: KtNamedFunction) {
                val lines = function.bodyBlockExpression?.statements ?: return

                if (lines.size > MAX_LINES) {
                    // 🔥 Show notification once
                    val notification = Notification(
                        "My Plugin Notifications",
                        "Code Inspection Triggered",
                        "Function '${function.name}' exceeds $MAX_LINES lines!",
                        NotificationType.WARNING
                    )
                    Notifications.Bus.notify(notification)

                    // ❌ Show errors on each extra line (adds squiggly lines)
                    lines.drop(MAX_LINES).forEach { statement ->
                        holder.registerProblem(
                            statement,
                            "❌ Too many lines in function! Consider refactoring.",
                            ProblemHighlightType.GENERIC_ERROR_OR_WARNING  // Red squiggly lines on each extra line
                        )
                    }
                }
            }
        }
    }
}
