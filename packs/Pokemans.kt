#!/usr/bin/env kscript
@file:DependsOn("moe.nikky.voodoo:dsl:0.4.+")
@file:DependsOn("ch.qos.logback:logback-classic:1.3.0-alpha4") //seems that i need a explicit dependency on this.. yet another bugreport
@file:MavenRepository("kotlinx", "https://kotlin.bintray.com/kotlinx")
@file:MavenRepository("elytradev", "https://repo.elytradev.com")
@file:KotlinOpts("-J-Xmx5g")
@file:KotlinOpts("-J-server")
@file:Include("../.voodoo/Mod.kt")
@file:Include("../.voodoo/TexturePack.kt")
@file:Include("../.voodoo/Forge.kt")

//COMPILER_OPTS -jvm-target 1.8

import com.skcraft.launcher.model.modpack.Recommendation
import voodoo.data.Side
import voodoo.data.curse.FileType
import voodoo.provider.CurseProvider
import voodoo.provider.DirectProvider
import voodoo.provider.JenkinsProvider
import voodoo.withDefaultMain

fun main(args: Array<String>) = withDefaultMain(
    arguments = args,
    root = Constants.rootDir
) {
    nestedPack(
        id = "pokemans",
        mcVersion = "1.10.2"
    ) {
        title = "Pokemans Reloaded"
        version = "1.0"
        icon = rootDir.resolve("icon.png")
        authors = listOf("capitalthree", "NikkyAi")
        forge = Forge.mc1_10_2.build2422
        root = rootEntry(CurseProvider) {
            releaseTypes = setOf(FileType.RELEASE, FileType.BETA)
            list {
                //TODO: group mods by category (eg. tweakers)
                add(Mod.abyssalcraft)
                add(Mod.advancedRocketry) configure {
                    releaseTypes = setOf(FileType.RELEASE, FileType.BETA, FileType.ALPHA)
                }
                add(Mod.apricornTreeFarm)
                add(Mod.armorplus)
                add(Mod.betterfps)
                add(Mod.chiselsBits)
                add(Mod.crafttweaker)
                add(Mod.customNpcs)
                add(Mod.enderIo)
                add(Mod.extraBitManipulation)
                add(Mod.farseek)
                add(Mod.foamfixForMinecraft)
                add(Mod.immersiveEngineering)
                add(Mod.industrialCraft)
                add(Mod.ivtoolkit)
                add(Mod.jei)
                add(Mod.lingeringLoot)
                add(Mod.minecolonies)
                add(Mod.minecraftFlightSimulator)
                add(Mod.modtweaker)
                add(Mod.multiMine)
                add(Mod.openmodularturrets)
                add(Mod.pamsHarvestcraft)
                add(Mod.quark)
                add(Mod.railcraft)
                add(Mod.recurrentComplex)
                add(Mod.repose)
                add(Mod.roguelikeDungeons)
                add(Mod.streams)
                add(Mod.structuredCrafting)
                add(Mod.tails)
                add(Mod.tinkersConstruct)
                add(Mod.timberjack)
                add(Mod.wearableBackpacks)

                withProvider(DirectProvider)
                    .list {
                        +"pixelmonDark" configure {
                            url =
                                "https://meowface.org/craft/repo/objects/db/5d/db5db11bcda204362d62705b1d5f4e5783f95c2c"
                            fileName = "PixelmonDark2.4.jar"
                        }
                        +"gameShark" configure {
                            url =
                                "https://meowface.org/craft/repo/objects/b9/21/b9216143fd5214c31e109b24fb1513eb8b23bc77"
                            fileName = "Gameshark-1.10.2-5.0.0.jar"
                        }
//                            add("gameShark") url "https://pixelmonmod.com/mirror/sidemods/gameshark/5.2.0/gameshark-1.12.2-5.2.0-universal.jar"
//                    }
                    }

                group {
                    side = Side.CLIENT
                }.list {
                    group {
                        feature {
                            selected = true
                            recommendation = Recommendation.starred
                        }
                    }.list {
                        add(Mod.xaerosMinimap) configure {
                            description = "lightweight minimap"
                        }
                        // infix notation
//                        add(Mod.xaerosMinimap) description "lightweight minimap"
                    }
                    group {
                        feature {
                            selected = false
                        }
                    }.list {
                        //TODO: add Optifine
                    }
                }

                withProvider(JenkinsProvider) {
                    jenkinsUrl = "https://ci.elytradev.com"
                }.list {
                    +"probe-data-provider" job "elytra/ProbeDataProvider/1.10.2"
                    +"fruit-phone" job "elytra/FruitPhone/1.10.2"

                    group {
                        side = Side.SERVER
                    }.list {
                    }
                }
            }
        }
}
}

