package com.intelij.AllAboutPlugin.networking

import com.intellij.openapi.ui.DialogWrapper
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.awt.BorderLayout
import javax.swing.*

class ApiDialog : DialogWrapper(true) {
    private val textArea = JTextArea(15, 50) // Text area to display API response
    private val fetchButton = JButton("Fetch API Response")

    init {
        title = "API Response"
        init() // Required for DialogWrapper
        fetchButton.addActionListener { fetchApiResponse() }
    }

    override fun createCenterPanel(): JComponent {
        val panel = JPanel(BorderLayout())
        panel.add(JScrollPane(textArea), BorderLayout.CENTER)
        panel.add(fetchButton, BorderLayout.SOUTH)
        return panel
    }

    private fun fetchApiResponse() {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://reqres.in/api/users?page=2")
            .build()

        Thread {
            try {
                val response: Response = client.newCall(request).execute()
                val responseBody = response.body?.string() ?: "No response"

                SwingUtilities.invokeLater {
                    textArea.text = responseBody
                }
            } catch (e: Exception) {
                SwingUtilities.invokeLater {
                    textArea.text = "Error: ${e.message}"
                }
            }
        }.start()
    }
}