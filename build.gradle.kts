import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.2.71"
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
        maven { setUrl("https://kotlin.bintray.com/kotlinx") }
    }
}

val dslVersion: String by project
val genSrc = buildDir.resolve("gen").apply { mkdirs() }
val genProject = project(".gen") {
    dependencies {
//        compile(group = "moe.nikky.voodoo-rewrite", name = "dsl", version = dslVersion)
        compile(group = "moe.nikky.voodoo", name = "dsl", version = "0.4.0")
    }
    kotlin.sourceSets.maybeCreate("main").kotlin.srcDir(genSrc.path)
    idea {
        module {
            generatedSourceDirs.add(genSrc)
        }
    }
}

val packDir: String by project
kotlin.sourceSets.maybeCreate("main").kotlin.srcDir(rootDir.resolve(packDir).apply { mkdirs() })

val poet = task<JavaExec>("poet") {
    main = "PoetKt"
    args = listOf(genSrc.path)
    classpath = genProject.sourceSets["main"].runtimeClasspath
    this.description = "generate curse mod listing"
    this.group = "build"
    dependsOn("${genProject.path}:classes")
}

val classes by tasks.getting(Task::class) {
    dependsOn(poet)
}

dependencies {
    compile(genProject)
    compile(group = "com.github.holgerbrandl", name = "kscript-annotations", version = "1.+")
}

application {
    // set this if you are gonna deal with just a single pack
    mainClassName = "YouModpackFileKt"
}

task<JavaExec>("cotm") {
    classpath = sourceSets["main"].runtimeClasspath
    main = "CotMKt"
    this.description = "Center of the Multiverse"
    this.group = "application"
}
task<JavaExec>("pokemans") {
    classpath = sourceSets["main"].runtimeClasspath
    main = "PokemansKt"
    this.description = "Pokemans"
    this.group = "application"
}