import org.jetbrains.kotlin.gradle.dsl.Coroutines

plugins {
    id("kotlin-platform-common") version "1.2.70"
}

dependencies {
    compile(kotlin("stdlib-common"))
    compile(group = "org.jetbrains.kotlinx", name = "kotlinx-coroutines-core", version = "0.26.1")
}

kotlin.experimental.coroutines = Coroutines.ENABLE
