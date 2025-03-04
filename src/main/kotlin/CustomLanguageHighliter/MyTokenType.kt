package com.intelij.AllAboutPlugin.CustomLanguageHighliter

import com.intellij.psi.tree.IElementType


object MyTokenTypes {
    val KEYWORD = IElementType("KEYWORD", MyLanguage)
    val IDENTIFIER = IElementType("IDENTIFIER", MyLanguage)
    val NUMBER = IElementType("NUMBER", MyLanguage)
    val STRING = IElementType("STRING", MyLanguage)
    val OPERATOR = IElementType("OPERATOR", MyLanguage)
    val COMMENT = IElementType("COMMENT", MyLanguage)
    val UNKNOWN = IElementType("UNKNOWN", MyLanguage)
}
