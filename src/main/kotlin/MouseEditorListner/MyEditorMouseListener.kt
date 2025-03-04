package com.intelij.AllAboutPlugin.MouseEditorListener

import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.editor.event.EditorMouseEvent
import com.intellij.openapi.editor.event.EditorMouseListener

class MyEditorMouseListener : EditorMouseListener {

    private val log = Logger.getInstance(MyEditorMouseListener::class.java)

    override fun mouseClicked(event: EditorMouseEvent) {
        log.info("Mouse clicked at: ${event.mouseEvent.point}") // ✅ Log mouse click

        // ✅ Show IntelliJ notification on mouse click
        val notificationGroup = NotificationGroupManager.getInstance()
            .getNotificationGroup("My Plugin Notifications")

        notificationGroup.createNotification(
            "Mouse Clicked at: ${event.mouseEvent.point}",
            NotificationType.INFORMATION
        ).notify(event.editor.project)
    }
}
