package com.intelij.AllAboutPlugin.PluginBassic

import com.intellij.icons.AllIcons
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.StatusBar
import com.intellij.openapi.wm.StatusBarWidget
import com.intellij.openapi.wm.StatusBarWidgetFactory
import com.intellij.openapi.wm.StatusBarWidget.Multiframe
import com.intellij.openapi.wm.StatusBarWidget.WidgetPresentation
import com.intellij.util.Consumer
import java.awt.event.MouseEvent
import javax.swing.Icon

// ✅ Implementing IconPresentation
class MyStatusBarWidget : StatusBarWidget, StatusBarWidget.IconPresentation, Multiframe {

    override fun ID(): String = "MyStatusBarWidget"

    override fun getPresentation(): WidgetPresentation = this

    override fun getTooltipText(): String = "Custom Status Bar Widget"

    override fun getIcon(): Icon = AllIcons.General.Information // ✅ Returns IntelliJ's info icon

    override fun install(statusBar: StatusBar) {}

    override fun dispose() {}

    override fun getClickConsumer(): Consumer<MouseEvent>? {
        return Consumer {
            println("Status bar widget clicked!")
        }
    }

    override fun copy(): StatusBarWidget = MyStatusBarWidget()
}

// ✅ Factory to Register the Widget
class MyStatusBarWidgetFactory : StatusBarWidgetFactory {

    override fun getId(): String = "MyStatusBarWidget"

    override fun getDisplayName(): String = "My Custom Widget"

    override fun isAvailable(project: Project): Boolean = true

    override fun createWidget(project: Project): StatusBarWidget {
        println("✅ MyStatusBarWidgetFactory is creating the widget!")
        return MyStatusBarWidget()
    }
    override fun disposeWidget(widget: StatusBarWidget) {}

    override fun canBeEnabledOn(statusBar: StatusBar): Boolean = true
}
