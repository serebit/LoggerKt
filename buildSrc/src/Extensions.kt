package com.serebit.logkat.buildsrc

import groovy.util.Node
import org.gradle.api.Project
import org.gradle.api.artifacts.repositories.MavenArtifactRepository
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.jvm.tasks.Jar
import org.gradle.kotlin.dsl.creating
import org.gradle.kotlin.dsl.maven
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

private const val projectName = "logkat"
private const val projectDescription = "A lightweight and simple Kotlin logger"
private val projectDevelopers = listOf("serebit")

fun KotlinDependencyHandler.api(group: String, name: String, version: String) = api("$group:$name:$version")
fun KotlinDependencyHandler.implementation(group: String, name: String, version: String) =
    implementation("$group:$name:$version")

fun PublishingExtension.createBintrayRepositories() {
    fun MavenArtifactRepository.applyCredentials() = credentials {
        username = "serebit"
        System.getenv("BINTRAY_KEY")?.let { password = it }
    }

    // create public
    repositories.maven("https://api.bintray.com/maven/serebit/public/$projectName/;publish=0") {
        name = "public"
        applyCredentials()
    }

    // create snapshot
    repositories.maven("https://api.bintray.com/maven/serebit/snapshot/$projectName/;publish=1") {
        name = "snapshot"
        applyCredentials()
    }
}

fun Project.jarTask() = tasks.creating(Jar::class) {
    archiveClassifier.value(name.removeSuffix("Jar"))
}

private fun Node.add(key: String, value: String) = appendNode(key).setValue(value)
private inline fun Node.node(key: String, content: Node.() -> Unit) = appendNode(key).also(content)

fun MavenPublication.configureForMavenCentral(javadocJar: Jar, sourcesJar: Jar) {
    artifact(javadocJar)
    if (name == "kotlinMultiplatform") artifact(sourcesJar)

    pom.withXml {
        asNode().run {
            add("name", projectName)
            add("description", projectDescription)
            add("url", "https://gitlab.com/serebit/$projectName")
            node("organization") {
                add("name", "com.serebit")
                add("url", "https://serebit.com")
            }
            node("issueManagement") {
                add("system", "gitlab")
                add("url", "https://gitlab.com/serebit/$projectName/issues")
            }
            node("licenses") {
                node("license") {
                    add("name", "Apache License 2.0")
                    add("url", "https://gitlab.com/serebit/$projectName/blob/master/LICENSE.md")
                    add("distribution", "repo")
                }
            }
            node("scm") {
                add("url", "https://gitlab.com/serebit/$projectName")
                add("connection", "scm:git:git://gitlab.com/serebit/$projectName.git")
                add("developerConnection", "scm:git:ssh://gitlab.com/serebit/$projectName.git")
            }
            node("developers") {
                projectDevelopers.forEach {
                    node("developer") {
                        add("name", it)
                    }
                }
            }
        }
    }
}
