package com.intelij.AllAboutPlugin.PluginBassic

import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.components.JBPanel
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.components.JBTextArea
import com.intellij.ui.content.ContentFactory
import java.awt.BorderLayout
import javax.swing.Box
import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JPanel

class MyToolWindowFactory : ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val panel = JBPanel<JBPanel<*>>()
        panel.layout = BorderLayout()

        // ✅ Create a text area
        val textArea = JBTextArea(10, 40) // 10 rows, 40 columns
        panel.add(JBScrollPane(textArea), BorderLayout.CENTER)

        val buttonPanel = JPanel().apply {
            layout = BoxLayout(this, BoxLayout.Y_AXIS) // Vertical layout
        }

        // ✅ Button to get text content
        val button = JButton("Get Text")
        button.addActionListener {
            val content = textArea.text
            Messages.showMessageDialog("You wrote: $content", "Info", Messages.getInformationIcon())
        }

        val button2 = JButton("Click Me").apply {
            addActionListener {
                val content = textArea.text
                Messages.showMessageDialog("Content: $content", "Info", Messages.getInformationIcon())
            }
        }
        val serviceButton = JButton("Project name").apply {
            addActionListener {
                val myProjectService = project.service<MyProjectService>()
                Messages.showMessageDialog("Content: ${myProjectService.getProjectName()}", "Info", Messages.getInformationIcon())
            }
        }

        buttonPanel.add(button)
        buttonPanel.add(Box.createVerticalStrut(10)) // Adds spacing between buttons
        buttonPanel.add(button2)
        buttonPanel.add(Box.createVerticalStrut(10))
        buttonPanel.add(serviceButton)


        // ✅ Add button panel to the bottom of the main panel
        panel.add(buttonPanel, BorderLayout.SOUTH)


        // ✅ Add the panel to the tool window
        val contentFactory = ContentFactory.getInstance()
        val content = contentFactory.createContent(panel, "", false)
        toolWindow.contentManager.addContent(content)
    }
}
