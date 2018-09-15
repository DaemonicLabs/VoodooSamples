#!/usr/bin/env kscript
@file:DependsOnMaven("moe.nikky.voodoo-rewrite:dsl:0.4.0-121")
@file:DependsOnMaven("ch.qos.logback:logback-classic:1.3.0-alpha4") //seems that i need a explicit dependency on this.. yet another bugreport
@file:MavenRepository("kotlinx","https://kotlin.bintray.com/kotlinx" )
@file:MavenRepository("ktor","https://dl.bintray.com/kotlin/ktor" )
@file:MavenRepository("elytra","https://repo.elytradev.com" )

import voodoo.*
import voodoo.data.*
import voodoo.data.curse.*
import voodoo.data.nested.*
import voodoo.provider.*
import java.io.File

fun main(args: Array<String>) {
    withDefaultMain(root = File("run"), arguments = args) {
        NestedPack(
            id = "some-silly-pack",
            version = "1.0",
            mcVersion = "1.12.2",
            //TODO: type = File
            icon = "icon.png",
            authors = listOf("NikkyAi"),
            //TODO: type = {recommended, latest} | buildnumber, make sealed class
            forge = "recommended",
            root = rootEntry(CurseProvider) {
                optionals = false
                releaseTypes = setOf(FileType.RELEASE, FileType.BETA)

                //TODO: use type URL ?
                metaUrl = "https://curse.nikky.moe/api"
                entriesBlock {
                    id("botania") optionals false

                    id("rftools") {
                        optionals = false
                    }

                    entry(JenkinsProvider) {
                        jenkinsUrl = "https://ci.elytradev.com"
                        side = Side.SERVER
                    }.entriesBlock {
                        id("matterlink") job "elytra/MatterLink/master"
                        id("elytra/BTFU/multi-version")
                    }

                    id("tails")
                }
            }
        )
    }
}

