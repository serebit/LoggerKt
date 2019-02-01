import com.jfrog.bintray.gradle.BintrayExtension

plugins {
    kotlin("multiplatform") version "1.3.20"
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
    sourceSets["commonMain"].dependencies {
        implementation(kotlin("stdlib-common"))
    }
    sourceSets["commonTest"].dependencies {
        implementation(kotlin("test-common"))
        implementation(kotlin("test-annotations-common"))
    }
    jvm().compilations["main"].defaultSourceSet.dependencies {
        implementation(kotlin("stdlib-jdk8"))
    }
    jvm().compilations["test"].defaultSourceSet.dependencies {
        implementation(kotlin("test-junit"))
    }
    linuxX64("linux").compilations["main"].defaultSourceSet.kotlin.srcDir("src/posixMain/kotlin")
    macosX64("macos").compilations["main"].defaultSourceSet.kotlin.srcDir("src/posixMain/kotlin")
    mingwX64("mingw")
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
