import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.FilenameFilter

plugins {
    id("voodoo") version "0.4.0"
}

voodoo {
//    generatedSource = project.file(".src")
}

// only required for plugin dev
repositories {
    mavenLocal()
}

//kotlin.sourceSets.asMap.map { (name, source) ->
//    println(name)
//    println(source.kotlin.srcDirs)
//}
