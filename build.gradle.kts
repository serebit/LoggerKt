import com.serebit.logkat.buildsrc.*

plugins {
    kotlin("multiplatform") version "1.3.50"
    id("com.github.ben-manes.versions") version "0.27.0"
    `maven-publish`
}

group = "com.serebit.logkat"
version = "0.5.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

kotlin {
    sourceSets.commonMain.get().dependencies {
        implementation(kotlin("stdlib-common"))
        api(group = "com.soywiz.korlibs.klock", name = "klock", version = "1.7.5")
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
            implementation(kotlin("test-junit5"))
            implementation(group = "org.junit.jupiter", name = "junit-jupiter", version = "5.5.2")
        }
    }

    linuxX64()
}

tasks.withType<Test> { useJUnitPlatform() }

publishing {
    createBintrayRepositories()

    val javadocJar by jarTask()
    val sourcesJar by jarTask()

    publishing.publications.withType<MavenPublication>().all {
        // configure additional POM data for Maven Central
        configureForMavenCentral(javadocJar, sourcesJar)
    }
}
