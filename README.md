# AllAboutPlugins
![Kotlin](https://img.shields.io/badge/Kotlin-17-blue?style=flat-square)

🚀 **AllAboutPlugins** is a comprehensive guide and implementation of various plugin development features for IntelliJ-based IDEs. Whether you're customizing UI elements, creating new editor functionalities, or enhancing code insights, this project covers it all!

---

## 📋 Overview Table
| Feature                     | Description |
|-----------------------------|-------------|
| **Startup Activity**        | Runs during startup before the project is fully loaded. |
| **UI & Menu Customization** | Modify UI elements and define custom action groups. |
| **Tool Windows**            | Create custom tool windows for additional functionalities. |
| **Application Services**     | Register application-wide services. |
| **Project Services**        | Register project-specific services. |
| **Status Bar Widgets**      | Add widgets to the status bar. |
| **Custom File Types**       | Define new file types with custom syntax. |
| **Editor Actions & Listeners** | Handle editor events like mouse clicks and key presses. |
| **Code Inspections**        | Create custom code analysis tools. |
| **Networking**        | Performs Networking using OKHTTP3 |

---

## 🔹 **Startup Activity**
- Runs **before** the project is fully loaded.
- Ideal for initializing settings, services, or logging.

```kotlin
class MyStartupActivity : StartupActivity {
    override fun runActivity(project: Project) {
        println("Plugin initialized!")
    }
}
```

---

### 🎨 **UI & Menu Customization**
- Modify the **UI** and **menus** to enhance user experience.
- Use `actionGroup` to define new actions.

```xml
<group id="MyCustomGroup" text="My Actions" popup="true">
    <add-to-group group-id="MainMenu" anchor="last"/>
    <action id="MyAction" class="com.example.MyAction" text="Click Me!"/>
</group>
```

---

### 📌 **Tool Windows**
- Create **custom tool windows** for additional functionality.

```kotlin
class MyToolWindowFactory : ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val content = ContentFactory.getInstance().createContent(MyPanel(), "My Tool", false)
        toolWindow.contentManager.addContent(content)
    }
}
```

---

### 🔧 **Application & Project Services**
- `applicationService` → For **global** services.
- `projectService` → For **project-specific** services.

```kotlin
@State(name = "MyAppService", storages = [Storage("app.xml")])
class MyAppService : PersistentStateComponent<MyAppService.State> {
    data class State(var data: String = "")
}
```

---

### ⚡ **Status Bar Widgets**
- Add widgets to the **status bar** for quick information.

```kotlin
class MyStatusBarWidgetProvider : StatusBarWidgetProvider {
    override fun getWidget(project: Project): StatusBarWidget = MyWidget()
}
```

---

## 📝 **Custom Editor Features**

### 🗂 **Custom File Types**
- Define **new file types** with unique syntax and behavior.

```xml
<fileType name="MyLang" language="MyLanguage" extension="mylang"/>
```

---

### 🎯 **Editor Actions & Listeners**
- `editorActionHandler` → **Intercept** editor actions.
- `editorMouseListener` → **Handle mouse events** in the editor.

```kotlin
class MyEditorActionHandler : EditorActionHandler() {
    override fun execute(editor: Editor, dataContext: DataContext?) {
        println("Editor action triggered!")
    }
}
```

---

## 🔍 **Code Insights & Inspections**

### 🛠 **Custom Code Inspections**
- Detect **issues** and provide **quick fixes**.

```kotlin
class MyInspectionTool : LocalInspectionTool() {
    override fun checkFile(file: PsiFile, manager: InspectionManager, isOnTheFly: Boolean): Array<ProblemDescriptor> {
        return arrayOf(manager.createProblemDescriptor(file, "Custom warning!", true, ProblemHighlightType.WARNING, isOnTheFly))
    }
}
```

---

## 🚀 Get Started
1. Clone the repo:  
   ```sh
   git clone https://github.com/RobinKumar5986/AllAboutPlugins.git
   ```
2. Open in IntelliJ IDEA.
3. Build and run your plugin!

---

💡 **Want to contribute?** Feel free to submit PRs or issues!

🔗 **License:** MIT

🔗 **Docs:** [JetBrains Plugin Development](https://plugins.jetbrains.com/docs/intellij/welcome.html)

---
