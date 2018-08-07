import org.jetbrains.kotlin.gradle.dsl.Coroutines

plugins {
    id("konan") version "0.8"
    id("com.jfrog.bintray")
    `maven-publish`
}

dependencies {
    expectedBy(project(":common"))
}

konan.targets = mutableListOf("linux")

konanArtifacts {
    library("loggerkt") {
        srcFiles("src/main/kotlin")
    }
}
