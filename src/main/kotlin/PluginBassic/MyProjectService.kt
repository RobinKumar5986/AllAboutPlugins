package com.intelij.AllAboutPlugin.PluginBassic

import com.intellij.openapi.components.Service
import com.intellij.openapi.project.Project

@Service(Service.Level.PROJECT)
class MyProjectService(private val project: Project) {
    fun getProjectName(): String {
        return "Project: ${project.name}"
    }
}