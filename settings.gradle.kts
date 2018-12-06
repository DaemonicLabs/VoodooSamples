pluginManagement {
    repositories {
//        mavenLocal()
        maven(url = "https://repo.elytradev.com") { name = "elytradev" }
        maven(url = "https://kotlin.bintray.com/kotlinx") { name = "kotlinx" }
        maven(url = "https://jitpack.io") {
            name = "jitpack"
        }
        gradlePluginPortal()
    }
}
rootProject.name = "VoodooSamples"
