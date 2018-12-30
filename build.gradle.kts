import com.jfrog.bintray.gradle.BintrayExtension

plugins {
    kotlin("multiplatform") version "1.3.20-eap-25"
    id("com.github.ben-manes.versions") version "0.20.0"
    id("com.jfrog.bintray") version "1.8.4"
    `maven-publish`
}

group = "com.serebit"
version = "0.4.3"

repositories {
    jcenter()
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
}

kotlin {
    jvm()
    linuxX64("linux")
    macosX64("macos")
    mingwX64("mingw")

    sourceSets {
        get("commonMain").dependencies {
            implementation(kotlin("stdlib-common"))
        }
        get("commonTest").dependencies {
            implementation(kotlin("test-common"))
            implementation(kotlin("test-annotations-common"))
        }
        get("jvmMain").dependencies {
            implementation(kotlin("stdlib-jdk8"))
        }
        get("jvmTest").dependencies {
            implementation(kotlin("test"))
            implementation(kotlin("test-junit5"))
        }
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
