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
}


val gen_src = project("gen").file("src").apply {
    mkdirs()
}
val gen = project("gen") {
    dependencies {
        //    compile(group = "moe.nikky.voodoo-rewrite", name = "dsl", version = "0.4.0+")
        compile(group = "moe.nikky.voodoo", name = "dsl", version = "0.4.0")
        compile(group = "com.github.holgerbrandl", name = "kscript-annotations", version = "1.+")
    }

    kotlin.sourceSets.maybeCreate("main").kotlin.srcDir(gen_src.path)
    idea {
        module {
            generatedSourceDirs.add(gen_src)
        }
    }
}

dependencies {
    compile(gen)
}

application {
//    mainClassName = "TestPackKt"
    mainClassName = "PokemansKt"
}

kotlin.sourceSets.maybeCreate("main").kotlin.srcDir("packs")

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
