import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.2.70"
    application
    idea
}
val kotlin_version: String  by project
allprojects {

    apply {
        plugin("kotlin")
        plugin("idea")
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

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

    repositories {
        mavenCentral()
        jcenter()
        mavenLocal()
        maven { setUrl("https://repo.elytradev.com") }
        maven { setUrl("https://dl.bintray.com/kotlin/ktor") }
        maven { setUrl("https://kotlin.bintray.com/kotlinx") }
    }

    kotlin.sourceSets.maybeCreate("main").kotlin.srcDir("src")
}


dependencies {
    compile(project("gen"))
}

//val runDir = rootProject.file("run")
//runDir.mkdirs()
//val run by tasks.getting(JavaExec::class) {
//    workingDir = runDir
//}

application {
    mainClassName = "TestPackKt"
}

val gen_src = rootProject.file("gen").resolve("gen-src").apply {
    mkdirs()
}

val generateCurseData = project("gen").task<JavaExec>("generateCurseData") {
    main = "voodoo.CursePoetKt"
    args = listOf(gen_src.path)
    classpath = project("gen").sourceSets["main"].runtimeClasspath
    dependsOn("classes")
    this.description = "generate curse mod listing"
    this.group = "build"
}

val build by tasks.getting(Task::class) {
    dependsOn(generateCurseData)
}
