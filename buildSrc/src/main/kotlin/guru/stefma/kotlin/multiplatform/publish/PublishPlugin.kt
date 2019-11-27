package guru.stefma.kotlin.multiplatform.publish

import org.gradle.api.Plugin
import org.gradle.api.Project

class PublishPlugin : Plugin<Project> {

    override fun apply(project: Project): Unit = with(project) {
        pluginManager.apply("guru.stefma.kotlin.multiplatform.publish.framework")
        pluginManager.apply("guru.stefma.kotlin.multiplatform.publish.jar")

        tasks.register("publishToArtifactory") {
            it.dependsOn(
                    tasks.named("publishFatFrameworkToArtifactory"),
                    tasks.named("publishFrameworkJsonToArtifactory"),
                    tasks.named("publishJarToArtifactory")
            )
        }
    }

}