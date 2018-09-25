import com.jfrog.bintray.gradle.BintrayExtension
import com.jfrog.bintray.gradle.tasks.BintrayUploadTask
import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.dsl.Coroutines

plugins {
    id("kotlin-platform-jvm") version "1.2.71"
    id("org.jetbrains.dokka") version "0.9.17"
}

dependencies {
    expectedBy(project(":common"))
    compile(kotlin("stdlib-jdk8"))
    testCompile(group = "io.kotlintest", name = "kotlintest-runner-junit5", version = "3.1.10")
}

kotlin.experimental.coroutines = Coroutines.ENABLE

tasks.getByName<DokkaTask>("dokka") {
    moduleName = "jvm"
    outputDirectory = "$rootDir/public"
}
