package com.intelij.AllAboutPlugin.CustomLanguageHighliter

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon
import com.intellij.icons.AllIcons

object MyFileType : LanguageFileType(MyLanguage) {
    override fun getName(): String = "MyLang File"
    override fun getDescription(): String = "A custom file for MyLang"
    override fun getDefaultExtension(): String = "xyz"
    override fun getIcon(): Icon = AllIcons.FileTypes.Custom // Change the icon if needed
}
