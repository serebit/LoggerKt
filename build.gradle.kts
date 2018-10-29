import com.jfrog.bintray.gradle.BintrayExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

plugins {
    kotlin("multiplatform") version "1.3.0"
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

kotlin.sourceSets {
    getByName("commonMain").dependencies {
        implementation(kotlin("stdlib-common"))
        implementation(kotlinx("coroutines-core-common", version = "1.0.0"))
    }
    create("jvmMain") {
        dependsOn(getByName("commonMain"))
        dependencies {
            implementation(kotlin("stdlib-jdk8"))
            implementation(kotlinx("coroutines-core", version = "1.0.0"))
        }
    }
    create("jvmTest") {
        dependsOn(getByName("commonTest"))
        dependencies {
            implementation(kotlin("reflect"))
            implementation(group = "io.kotlintest", name = "kotlintest-runner-junit5", version = "3.1.10")
        }
    }
}

apply(from = "$rootDir/gradle/kotlin-targets.gradle")

bintray {
    user = "serebit"
    key = System.getenv("BINTRAY_KEY")
    setPublications("metadata", "jvm")
    pkg(delegateClosureOf<BintrayExtension.PackageConfig> {
        repo = "public"
        name = rootProject.name
        version.name = project.version.toString()
    })
}
