import com.jfrog.bintray.gradle.BintrayExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

plugins {
    kotlin("multiplatform") version "1.3.11"
    id("com.github.ben-manes.versions") version "0.20.0"
    id("com.jfrog.bintray") version "1.8.4"
    `maven-publish`
}

group = "com.serebit"
version = "0.4.2"

repositories {
    jcenter()
}

fun kotlin(module: String) = "org.jetbrains.kotlin:kotlin-$module"
fun kotlinx(module: String, version: String) = "org.jetbrains.kotlinx:kotlinx-$module:$version"
fun KotlinDependencyHandler.implementation(group: String, name: String, version: String) =
    implementation("$group:$name:$version")

apply(from = "$rootDir/gradle/kotlin-targets.gradle")

kotlin.sourceSets {
    getByName("commonMain").dependencies {
        implementation(kotlin("stdlib"))
    }
    getByName("commonTest").dependencies {
        implementation(kotlin("test-common"))
        implementation(kotlin("test-annotations-common"))
    }
    getByName("jvmMain").dependencies {
        implementation(kotlin("stdlib-jdk8"))
    }
    getByName("jvmTest").dependencies {
        implementation(kotlin("test"))
        implementation(kotlin("test-junit"))
    }
}

bintray {
    user = "serebit"
    key = System.getenv("BINTRAY_KEY")
    setPublications("metadata", "jvm", "linux")
    pkg(delegateClosureOf<BintrayExtension.PackageConfig> {
        repo = "public"
        name = rootProject.name
        version.name = project.version.toString()
    })
}
