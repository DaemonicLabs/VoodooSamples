import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.2.70"
    application
    idea
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

idea {
    module {
        excludeDirs.add(file("run"))
    }
}

val compileKotlin by tasks.getting(KotlinCompile::class) {
    // Customise the “compileKotlin” task.
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
repositories {
    mavenCentral()
    jcenter()
//    mavenLocal()
    maven { setUrl("https://repo.elytradev.com") }
    maven { setUrl("https://dl.bintray.com/kotlin/ktor") }
    maven { setUrl("https://kotlin.bintray.com/kotlinx") }
}
//val kotlin_version: String  = "1.2.60"
dependencies {
    compile(group = "moe.nikky.voodoo-rewrite", name = "dsl", version = "0.4.0+")
    compile(group = "com.github.holgerbrandl", name = "kscript-annotations", version = "1.2")
}

kotlin.sourceSets.maybeCreate("main").kotlin.srcDir("src")

//val runDir = rootProject.file("run")
//runDir.mkdirs()
//val run by tasks.getting(JavaExec::class) {
//    workingDir = runDir
//}

application {
    mainClassName = "TestPackKt"
}