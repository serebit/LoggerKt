import org.jetbrains.kotlin.gradle.dsl.Coroutines

plugins {
    id("kotlin-platform-common") version "1.2.51"
    id("io.gitlab.arturbosch.detekt")
}

dependencies {
    compile(group = "org.jetbrains.kotlin", name = "kotlin-stdlib-common")
    compile(group = "org.jetbrains.kotlinx", name = "kotlinx-coroutines-core", version = "0.23.4")
}

kotlin {
    experimental.coroutines = Coroutines.ENABLE
}

detekt {
    defaultProfile(Action {
        input = "$projectDir/src/main/kotlin"
        filters = ".*test.*,.*/resources/.*,.*/tmp/.*"
    })
}
