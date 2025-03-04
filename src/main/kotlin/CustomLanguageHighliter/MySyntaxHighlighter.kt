package com.intelij.AllAboutPlugin.CustomLanguageHighliter

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType

class MySyntaxHighlighter : SyntaxHighlighterBase() {
    override fun getHighlightingLexer(): Lexer = MyLexer()

    override fun getTokenHighlights(tokenType: IElementType?): Array<TextAttributesKey> {
        // ✅ Ensure tokenType is never null
        if (tokenType == null) return emptyArray()

        return when (tokenType) {
            MyTokenTypes.KEYWORD -> arrayOf(DefaultLanguageHighlighterColors.KEYWORD)
            MyTokenTypes.STRING -> arrayOf(DefaultLanguageHighlighterColors.STRING)
            MyTokenTypes.NUMBER -> arrayOf(DefaultLanguageHighlighterColors.NUMBER)
            MyTokenTypes.OPERATOR -> arrayOf(DefaultLanguageHighlighterColors.OPERATION_SIGN)
            MyTokenTypes.COMMENT -> arrayOf(DefaultLanguageHighlighterColors.LINE_COMMENT)
            else -> emptyArray()
        }
    }
}
