apply {
    plugin("idea")
}
dependencies {
    //    compile(group = "moe.nikky.voodoo-rewrite", name = "dsl", version = "0.4.0+")
    compile(group = "moe.nikky.voodoo", name = "dsl", version = "0.4.0")
    compile(group = "com.github.holgerbrandl", name = "kscript-annotations", version = "1.+")
}

val gen_src = project.file("gen-src")

kotlin.sourceSets.maybeCreate("main").kotlin.srcDir(gen_src.path)
idea {
    module {
        generatedSourceDirs.add(gen_src)
    }
}