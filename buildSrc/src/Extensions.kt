package com.serebit.logkat.gradle

import org.gradle.api.publish.PublishingExtension
import org.gradle.kotlin.dsl.maven
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

fun KotlinDependencyHandler.api(group: String, name: String, version: String) = api("$group:$name:$version")

fun PublishingExtension.configureBintray(
    userName: String,
    repositoryName: String,
    projectName: String,
    accessKey: String?
) = repositories.maven("https://api.bintray.com/maven/serebit/$repositoryName/$projectName/;publish=0") {
    name = "bintray"

    credentials {
        username = userName
        accessKey?.let { password = it }
    }
}

object Versions {
    const val KLOCK = "1.5.0"
}
