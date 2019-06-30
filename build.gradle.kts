import com.serebit.logkat.gradle.Versions
import com.serebit.logkat.gradle.api
import com.serebit.logkat.gradle.configureBintray

plugins {
    kotlin("multiplatform") version "1.3.40"
    id("com.github.ben-manes.versions") version "0.21.0"
    `maven-publish`
}

group = "com.serebit.logkat"
version = "0.4.6"

repositories {
    jcenter()
}

kotlin {
    sourceSets.commonMain.get().dependencies {
        implementation(kotlin("stdlib-common"))
        api(group = "com.soywiz.korlibs.klock", name = "klock", version = Versions.KLOCK)
    }
    sourceSets.commonTest.get().dependencies {
        implementation(kotlin("test-common"))
        implementation(kotlin("test-annotations-common"))
    }

    jvm {
        compilations["main"].defaultSourceSet.dependencies {
            implementation(kotlin("stdlib-jdk8"))
        }
        compilations["test"].defaultSourceSet.dependencies {
            implementation(kotlin("test-junit"))
        }
    }

    linuxX64()
}

publishing.configureBintray("serebit", "public", rootProject.name, System.getenv("BINTRAY_KEY"))
