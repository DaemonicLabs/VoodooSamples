pluginManagement {
    repositories {
//        mavenLocal()
        maven(url = "https://repo.elytradev.com") { name = "elytradev" }
        maven(url = "https://dl.bintray.com/kotlin/kotlin-eap") { name = "kotlin-eap" }
        maven(url = "https://kotlin.bintray.com/kotlinx") { name = "kotlinx" }
        gradlePluginPortal()
    }
}
rootProject.name = "VoodooSamples"
