plugins {
    kotlin("jvm") version "1.3.60"
    `java-gradle-plugin`
}

gradlePlugin {
    plugins {
        register("frameworkPublish") {
            id = "guru.stefma.kotlin.multiplatform.publish.framework"
            implementationClass = "guru.stefma.kotlin.multiplatform.publish.FrameworkPlugin"
        }
        register("jarPublish") {
            id = "guru.stefma.kotlin.multiplatform.publish.jar"
            implementationClass = "guru.stefma.kotlin.multiplatform.publish.JarPlugin"
        }
        register("frameworkJarPublish") {
            id = "guru.stefma.kotlin.multiplatform.publish"
            implementationClass = "guru.stefma.kotlin.multiplatform.publish.PublishPlugin"
        }
    }
}

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}