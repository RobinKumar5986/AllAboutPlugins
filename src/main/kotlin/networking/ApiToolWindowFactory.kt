package com.intelij.AllAboutPlugin.networking

import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.components.JBTextArea
import com.intellij.ui.content.ContentFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import javax.swing.*

class ApiToolWindowFactory : ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val panel = JPanel()
        panel.layout = BoxLayout(panel, BoxLayout.Y_AXIS)

        val textArea = JBTextArea(15, 50)
        textArea.isEditable = false

        val button = JButton("Fetch API Response")
        button.addActionListener {
            fetchApiResponse(textArea)
        }

        panel.add(JBScrollPane(textArea))
        panel.add(button)

        val contentFactory = ContentFactory.getInstance()
        val content = contentFactory.createContent(panel, "", false)
        toolWindow.contentManager.addContent(content)
    }

    private fun fetchApiResponse(textArea: JBTextArea) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://reqres.in/api/users?page=2")
            .build()

        Thread {
            try {
                val response: Response = client.newCall(request).execute()
                val responseBody = response.body?.string() ?: "No response"

                SwingUtilities.invokeLater {
                    val formatedString = toPrettyFormat(responseBody)
                    textArea.text = formatedString
                }
            } catch (e: Exception) {
                SwingUtilities.invokeLater {
                    textArea.text = "Error: ${e.message}"
                }
            }
        }.start()
    }
    fun toPrettyFormat(jsonString: String): String {
        val jsonElement = JsonParser.parseString(jsonString)
        val gson = GsonBuilder().setPrettyPrinting().create()
        return gson.toJson(jsonElement)
    }

}
