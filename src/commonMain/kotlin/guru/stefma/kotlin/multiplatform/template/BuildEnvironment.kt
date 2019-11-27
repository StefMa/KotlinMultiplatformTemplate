package guru.stefma.kotlin.multiplatform.template

internal expect val internalEnvironment: String

data class BuildEnvironment(
        val version: Int = 1,
        val environment: String = internalEnvironment
)
