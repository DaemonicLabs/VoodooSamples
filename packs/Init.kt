#!/usr/bin/env kscript
@file:DependsOn("moe.nikky.voodoo:dsl:0.4.0-SNAPSHOT")
@file:DependsOn("ch.qos.logback:logback-classic:1.3.0-alpha4") //seems that i need a explicit dependency on this.. yet another bugreport
@file:MavenRepository("kotlinx", "https://kotlin.bintray.com/kotlinx")
@file:MavenRepository("elytradev", "https://repo.elytradev.com")

import moe.nikky.voodoo.poet
import java.io.File

fun main(args: Array<String>) {
    poet(root = File("gen").resolve("src"))
}