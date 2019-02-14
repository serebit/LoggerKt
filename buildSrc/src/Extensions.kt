package com.serebit.logkat.gradle

import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.kotlin.dsl.maven
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

fun KotlinDependencyHandler.api(group: String, name: String, version: String) = api("$group:$name:$version")
fun KotlinDependencyHandler.implementation(group: String, name: String, version: String) =
    implementation("$group:$name:$version")

fun RepositoryHandler.kotlinEap() = maven("https://dl.bintray.com/kotlin/kotlin-eap")
fun RepositoryHandler.soywiz() = maven("https://dl.bintray.com/soywiz/soywiz")

object Versions {
    const val KLOCK = "1.2.1"
}
