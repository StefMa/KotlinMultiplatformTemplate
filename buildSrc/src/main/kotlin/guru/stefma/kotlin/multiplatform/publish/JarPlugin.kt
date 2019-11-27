package guru.stefma.kotlin.multiplatform.publish

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension

class JarPlugin : Plugin<Project> {

    override fun apply(project: Project): Unit = with(project) {
        pluginManager.apply("maven-publish")

        extensions.getByType(PublishingExtension::class.java).apply {
            repositories { repoHandler ->
                repoHandler.maven {
                    it.name = "Artifactory"
                    it.url = project.uri("$buildDir/artifactory")
                }
            }
        }

        tasks.register("publishJarToArtifactory") {
            it.dependsOn(
                    tasks.named("publishMetadataPublicationToArtifactoryRepository"),
                    tasks.named("publishJvmPublicationToArtifactoryRepository")
            )
        }

    }
}