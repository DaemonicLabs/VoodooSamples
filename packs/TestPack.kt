#!/usr/bin/env kscript
@file:DependsOn("moe.nikky.voodoo:dsl:0.4.0-SNAPSHOT")
@file:DependsOn("ch.qos.logback:logback-classic:1.3.0-alpha4") //seems that i need a explicit dependency on this.. yet another bugreport
@file:MavenRepository("kotlinx", "https://kotlin.bintray.com/kotlinx")
@file:MavenRepository("elytradev", "https://repo.elytradev.com")
@file:Include("../.voodoo/Mod.kt")
@file:Include("../.voodoo/TexturePack.kt")
@file:Include("../.voodoo/Forge.kt")

//COMPILER_OPTS -jvm-target 1.8

import voodoo.data.Side
import voodoo.data.curse.FileType
import voodoo.data.nested.NestedPack
import voodoo.provider.CurseProvider
import voodoo.provider.JenkinsProvider
import java.io.File

fun main(args: Array<String>) {
    withDefaultMain(root = File("testpack"), arguments = args) {
        NestedPack(
            id = "testpack",
            version = "1.0",
            mcVersion = "1.12.2",
            //TODO: type = File
            icon = root.resolve("icon.png"),
            authors = listOf("NikkyAi"),
            //TODO: type = {recommended, latest} | buildnumber, make sealed class
            forge = Forge.recommended,
            root = rootEntry(CurseProvider) {
                optionals = false
                releaseTypes = setOf(FileType.RELEASE, FileType.BETA)

                //TODO: use type URL ?
                metaUrl = "https://curse.nikky.moe/api"
                list {
                    id(Mod::botania) optionals false

                    id(Mod::rftools) {
                        optionals = false
                    }

                    withProvider(JenkinsProvider) {
                        jenkinsUrl = "https://ci.elytradev.com"
                        side = Side.SERVER
                    }.list {
                        id("matterlink") job "elytra/MatterLink/master"
                        id("elytra/BTFU/multi-version")
                    }

                    id(Mod::tails)
                    id(Mod::wearableBackpacks)
                }
            }
        )
    }
}

