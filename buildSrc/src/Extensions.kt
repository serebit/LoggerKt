package com.serebit.logkat.buildsrc

import org.gradle.api.artifacts.repositories.MavenArtifactRepository
import org.gradle.api.publish.PublishingExtension
import org.gradle.kotlin.dsl.maven
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

fun KotlinDependencyHandler.api(group: String, name: String, version: String) = api("$group:$name:$version")
fun KotlinDependencyHandler.implementation(group: String, name: String, version: String) =
    implementation("$group:$name:$version")

fun PublishingExtension.createBintrayRepository(accessKey: String?): MavenArtifactRepository =
    repositories.maven("https://api.bintray.com/maven/serebit/public/${ProjectInfo.name}/;publish=0") {
        name = "bintray"

        credentials {
            username = "serebit"
            accessKey?.let { password = it }
        }
    }
