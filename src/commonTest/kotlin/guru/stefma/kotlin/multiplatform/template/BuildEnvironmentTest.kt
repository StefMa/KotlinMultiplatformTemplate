package guru.stefma.kotlin.multiplatform.template

import kotlin.test.Test

internal class BuildEnvironmentTest {

    @Test
    fun testPrintEnvironment() {
        val (version, environment) = BuildEnvironment()

        println("Version: $version, Environment: $environment")
    }
}
