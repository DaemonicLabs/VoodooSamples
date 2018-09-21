#!/usr/bin/env kscript
@file:DependsOnMaven("moe.nikky.voodoo-rewrite:dsl:0.4.0-139")
@file:DependsOnMaven("ch.qos.logback:logback-classic:1.3.0-alpha4") //seems that i need a explicit dependency on this.. yet another bugreport
@file:MavenRepository("kotlinx","https://kotlin.bintray.com/kotlinx" )
@file:MavenRepository("ktor", "https://dl.bintray.com/kotlin/ktor" )
@file:MavenRepository("elytradev", "https://repo.elytradev.com" )

import voodoo.cursePoet
import java.io.File

fun main(args: Array<String>) {
    cursePoet(root = File("gen").resolve("src"))
}