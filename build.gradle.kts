import com.jfrog.bintray.gradle.BintrayExtension
import com.serebit.logkat.gradle.Versions
import com.serebit.logkat.gradle.api
import com.serebit.logkat.gradle.kotlinEap
import com.serebit.logkat.gradle.soywiz

plugins {
    kotlin("multiplatform") version "1.3.21"
    id("com.github.ben-manes.versions") version "0.21.0"
    id("com.jfrog.bintray") version "1.8.4"
    `maven-publish`
}

group = "com.serebit"
version = "0.4.4"

repositories {
    jcenter()
    kotlinEap()
    soywiz()
}

kotlin {
    sourceSets.commonMain.get().dependencies {
        implementation(kotlin("stdlib-common"))
        api(group = "com.soywiz", name = "klock-metadata", version = Versions.KLOCK)
    }
    sourceSets.commonTest.get().dependencies {
        implementation(kotlin("test-common"))
        implementation(kotlin("test-annotations-common"))
    }

    jvm().compilations["main"].defaultSourceSet.dependencies {
        implementation(kotlin("stdlib-jdk8"))
        api(group = "com.soywiz", name = "klock-jvm", version = Versions.KLOCK)
    }
    jvm().compilations["test"].defaultSourceSet.dependencies {
        implementation(kotlin("test-junit"))
    }

    linuxX64("linux").compilations["main"].defaultSourceSet {
        kotlin.srcDir("src/posixMain/kotlin")
        dependencies {
            api(group = "com.soywiz", name = "klock-linuxx64", version = Versions.KLOCK)
        }
    }

    macosX64("macos").compilations["main"].defaultSourceSet {
        kotlin.srcDir("src/posixMain/kotlin")
        dependencies {
            api(group = "com.soywiz", name = "klock-macosx64", version = Versions.KLOCK)
        }
    }

    mingwX64("mingw").compilations["main"].defaultSourceSet.dependencies {
        api(group = "com.soywiz", name = "klock-mingwx64", version = Versions.KLOCK)
    }
}

bintray {
    user = "serebit"
    key = System.getenv("BINTRAY_KEY")
    System.getenv("BINTRAY_PUBLICATION")?.let { setPublications(it) }
    pkg(delegateClosureOf<BintrayExtension.PackageConfig> {
        repo = "public"
        name = rootProject.name
        version.name = project.version.toString()
    })
}
