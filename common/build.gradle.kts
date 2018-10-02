import org.jetbrains.kotlin.gradle.dsl.Coroutines

plugins {
    id("kotlin-platform-common") version "1.2.71"
}

dependencies {
    compile(kotlin("stdlib-common"))
    compile(kotlinx("coroutines-core-common", version = "0.30.0"))
}

kotlin.experimental.coroutines = Coroutines.ENABLE

fun kotlinx(module: String, version: String): Any = "org.jetbrains.kotlinx:kotlinx-$module:$version"
