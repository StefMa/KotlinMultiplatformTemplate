# A Kotlin Multiplatform template
This Kotlin Multiplatform can be used as a template to get started with MPP in your project.

This is basically the implementation part of my Medium posts:
* ["Our use case of using Kotlin Multiplatform"](https://medium.com/@StefMa/our-use-case-of-using-kotlin-multiplatform-5359c75fad71)
* ["Our Kotlin Multiplatform implementation"](https://medium.com/@StefMa/our-kotlin-multiplatform-implementation-c99ae369a0f3)

## Details
This Multiplatform project has two targets: JVM and iOS.
To be concrete is has two iOS targets so that the generated Framework can be build on a simulator and on a real iOS device,
a macos64 target to test the native implementation and a JVM target.

The produced artifacts (the JAR and the Framework) are intended to be published to a repository. 
Therefore it exist a Gradle Plugin (actually three) inside the `buildSrc/` which takes care of it.

The setup of the `build.gradle.kts` is pretty minimal.
As said above it defines only two targets with the "default" dependencies for building and testing the project.

## Explore this project
You can checkout this repository and check how it feels for you.
There are some Gradle tasks which are probably important for you:
* `releaseFatFramework` 
* `publishToArtifactory`
* `jvmTest`
* `macosX64Test`

The `publishToArtifactory` task will "publish" the FatFramework and the JAR file to a fake Artifactory
which is available at `build/artifactory` afterwards.

## Plans on this template
State today I'm not sure if and how I could improve this project while keeping
the minimal implementations details for setting up a Kotlin Multiplatform project.

Also because this project is somehow "connected" to my Blog post.

But anyway lets see what the future brings 😉.

