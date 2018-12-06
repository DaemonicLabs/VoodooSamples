plugins {
    id("voodoo") version "0.4.4-SNAPSHOT"
}

voodoo {
////    generatedSource = project.file(".src")
////    packDirectory = project.file("packs")

    addTask(name = "build", parameters = listOf("build"))
    addTask(name = "sk", parameters = listOf("pack sk"))
    addTask(name = "server", parameters = listOf("pack server"))
    addTask(name = "buildAndPackAll", parameters = listOf("build", "pack sk", "pack server", "pack mmc"))
}

// only required for plugin dev
//repositories {
//    mavenLocal()
//}

tasks.withType<Wrapper> {
    gradleVersion = "5.0"
    distributionType = Wrapper.DistributionType.ALL
}