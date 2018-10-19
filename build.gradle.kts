plugins {
////    id("voodoo") version "0.4.2-SNAPSHOT"
    id("voodoo") version "0.4.2-SNAPSHOT"
}

voodoo {
////    generatedSource = project.file(".src")
////    packDirectory = project.file("packs")
}

// only required for plugin dev
//repositories {
//    mavenLocal()
//}

tasks.withType<Wrapper> {
    gradleVersion = "5.0-milestone-1"
    distributionType = Wrapper.DistributionType.ALL
}