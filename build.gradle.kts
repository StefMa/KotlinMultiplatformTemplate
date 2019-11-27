import org.jetbrains.kotlin.gradle.tasks.FatFrameworkTask

plugins {
    kotlin("multiplatform") version "1.3.60"
    id("guru.stefma.kotlin.multiplatform.publish")
}

val frameworkName = "SomeFrameworkName"
version = "1.0.1"
group = "guru.stefma.kotlin.multiplatform"

repositories {
    mavenCentral()
}

kotlin {
    jvm()

    iosArm64 {
        binaries.framework {
            baseName = frameworkName
        }
    }

    iosX64 {
        binaries.framework {
            baseName = frameworkName
        }
    }

    macosX64()
}

val jvmMainImplementation by configurations
val jvmTestImplementation by configurations
dependencies {
    commonMainImplementation(kotlin("stdlib-common"))

    commonTestImplementation(kotlin("test-common"))
    commonTestImplementation(kotlin("test-annotations-common"))

    jvmMainImplementation(kotlin("stdlib-jdk8"))

    jvmTestImplementation(kotlin("test:1.3.60"))
    jvmTestImplementation(kotlin("test-junit:1.3.60"))
}

val fatFrameworkTask = tasks.register("releaseFatFramework", FatFrameworkTask::class) {
    baseName = frameworkName

    from(
            kotlin.iosArm64().binaries.getFramework("RELEASE"),
            kotlin.iosX64().binaries.getFramework("RELEASE")
    )
}
