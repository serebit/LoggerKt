import com.serebit.logkat.buildsrc.*

plugins {
    kotlin("multiplatform") version "1.3.50"
    id("com.github.ben-manes.versions") version "0.25.0"
    `maven-publish`
}

group = ProjectInfo.group
version = ProjectInfo.version

repositories {
    mavenCentral()
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
            implementation(kotlin("test-junit5"))
            implementation(group = "org.junit.jupiter", name = "junit-jupiter", version = Versions.JUPITER)
        }
    }

    linuxX64()
}

tasks.withType<Test> { useJUnitPlatform() }

publishing.createBintrayRepository(System.getenv("BINTRAY_KEY"))
