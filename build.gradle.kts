import com.jfrog.bintray.gradle.BintrayExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

plugins {
    kotlin("multiplatform") version "1.3.0-rc-146"
    id("com.github.ben-manes.versions") version "0.20.0"
    id("com.jfrog.bintray") version "1.8.4"
    `maven-publish`
}

group = "com.serebit"
version = "0.4.2"

repositories {
    jcenter()
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
}

fun kotlin(module: String) = "org.jetbrains.kotlin:kotlin-$module"
fun kotlinx(module: String, version: String) = "org.jetbrains.kotlinx:kotlinx-$module:$version"
fun KotlinDependencyHandler.implementation(group: String, name: String, version: String) =
    implementation("$group:$name:$version")

kotlin.sourceSets {
    getByName("commonMain").dependencies {
        implementation(kotlin("stdlib-common"))
        implementation(kotlinx("coroutines-core-common", version = "0.30.2-eap13"))
    }
    create("jvmMain").dependencies {
        implementation(kotlin("stdlib-jdk8"))
        implementation(kotlinx("coroutines-core", version = "0.30.2-eap13"))
    }
    create("jvmTest").dependencies {
        implementation(kotlin("reflect"))
        implementation(group = "io.kotlintest", name = "kotlintest-runner-junit5", version = "3.1.10")
    }
}

apply(from = "$rootDir/gradle/kotlin-targets.gradle")

bintray {
    user = "serebit"
    key = System.getenv("BINTRAY_KEY")
    setPublications("metadata", "jvm")
    pkg(delegateClosureOf<BintrayExtension.PackageConfig> {
        repo = "test"
        name = rootProject.name
        version.name = project.version.toString()
    })
}
