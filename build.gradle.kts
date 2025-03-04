plugins {
    kotlin("jvm") version "1.9.23"
    id("org.jetbrains.intellij") version "1.16.0"
}

group = "com.codeWithRk.TtfFileReader"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(17) // Matches IntelliJ 2024.1.3 runtime
}

intellij {
    version.set("2024.1.3") // IntelliJ version
    type.set("IC") // Community Edition
    plugins.set(listOf("java", "org.jetbrains.kotlin")) // ✅ Add Kotlin Plugin
}

tasks {
    patchPluginXml {
        version.set(project.version.toString())
        sinceBuild.set("241") // Updated for IntelliJ 2024.1.x
        untilBuild.set("242.*") // Future-proofing
    }

    test {
        useJUnitPlatform()
    }

    runIde {
        jvmArgs = listOf("-Djb.consents.confirmation.enabled=false")
    }
}