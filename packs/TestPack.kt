import voodoo.data.Side
import voodoo.data.curse.FileType
import voodoo.provider.CurseProvider
import voodoo.provider.JenkinsProvider
import voodoo.withDefaultMain

fun main(args: Array<String>) {
    withDefaultMain(
        root = Constants.rootDir,
        arguments = args
    ) {
        nestedPack(
            id = "testpack",
            mcVersion = "1.12.2"
        ) {
            version = "1.0"
            //TODO: type = File
            icon = rootDir.resolve("icon.png")
            authors = listOf("NikkyAi")
            //TODO: type = {recommended, latest} | buildnumber, make sealed class
            forge = Forge.recommended
            root = rootEntry(CurseProvider) {
                releaseTypes = setOf(FileType.RELEASE, FileType.BETA)

                //TODO: use type URL ?
                metaUrl = "https://curse.nikky.moe/api"
                list {
                    +Mod.botania

                    +Mod.rftools configure {
                    }

                    withProvider(JenkinsProvider) {
                        jenkinsUrl = "https://ci.elytradev.com"
                        side = Side.SERVER
                    }.list {
                        +"matterlink" job "elytra/MatterLink/master"
                        +"elytra/BTFU/multi-version"
                    }

                    +Mod.tails
                    +Mod.wearableBackpacks
                }
            }
        }
    }
}

