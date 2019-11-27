package guru.stefma.kotlin.multiplatform.publish

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.bundling.Zip
import java.io.File

class FrameworkPlugin : Plugin<Project> {

    override fun apply(project: Project): Unit = with(project) {
        val zipFramework = tasks.register("zipFatFramework", Zip::class.java) {
            it.dependsOn(tasks.named("releaseFatFramework"))

            it.from("$buildDir/fat-framework")
            it.archiveFileName.set("SomeFramework.framework.zip")
            it.destinationDirectory.set(file("$buildDir/fat-framework-zip"))
        }

        tasks.register("publishFatFrameworkToArtifactory") {
            it.dependsOn(tasks.named("releaseFatFramework"), zipFramework)

            it.doLast {
                val frameworkDestinationDir = File("$buildDir/artifactory/$groupAsPath/SomeFramework/$version/").apply { mkdirs() }
                File("$buildDir/fat-framework-zip/SomeFramework.framework.zip")
                        .renameTo(File("$frameworkDestinationDir/SomeFramework.framework.zip"))
            }
        }

        val downloadAndModifyJson = tasks.register("downloadAndModifyFrameworkJson") {
            it.doLast {
                File("$buildDir/artifactory/$groupAsPath/SomeFramework/SomeFramework.json")
                        .renameTo(File("$buildDir/SomeFramework.json"))

                file("$buildDir/SomeFramework.json").apply {
                    if (exists()) {
                        val currentContent = readText()
                        if (currentContent.contains("\"$version\"")) return@apply
                        val replace = currentContent.replace("{", "").replace("}", "")
                        val newContent = "$replace, \"$version\" : \"$buildDir/artifactory/$groupAsPath/SomeFramework/$version/SomeFramework.framework.zip\""
                        writeText("{ $newContent }")
                    } else {
                        parentFile.mkdirs()
                        createNewFile()
                        writeText("""
                { "$version" : "$buildDir/artifactory/$groupAsPath/SomeFramework/$version/SomeFramework.framework.zip" }
                    """.trimIndent())
                    }
                }
            }
        }

        tasks.register("publishFrameworkJsonToArtifactory") {
            it.dependsOn(downloadAndModifyJson)

            it.doLast {
                File("$buildDir/SomeFramework.json")
                        .renameTo(File("$buildDir/artifactory/$groupAsPath/SomeFramework/SomeFramework.json"))
            }
        }
    }

    private val Project.groupAsPath
        get() = (group as String).replace(".", "/")
}
