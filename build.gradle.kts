import com.jfrog.bintray.gradle.BintrayExtension

plugins {
    kotlin("multiplatform") version "1.3.0-rc-146"
    id("com.github.ben-manes.versions") version "0.20.0"
    id("com.jfrog.bintray") version "1.8.4" apply false
    `maven-publish`
}

group = "com.serebit"
version = "0.4.2-eap13"

allprojects {
    repositories {
        jcenter()
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
    }
}

fun kotlin(module: String) = "org.jetbrains.kotlin:kotlin-$module"

kotlin {
    sourceSets {
        getByName("commonMain").dependencies {
            implementation(kotlin("stdlib-common"))
        }
        create("jvmMain").dependencies {
            implementation(kotlin("stdlib-jdk8"))
        }
        create("jvmTest").dependencies {
            implementation("io.kotlintest:kotlintest-runner-junit5:3.1.10")
        }
    }
}

apply(from = "$rootDir/gradle/kotlin-targets.gradle")

subprojects {
    apply(plugin = "maven-publish")
    apply(plugin = "java")

    val sourcesJar by tasks.creating(Jar::class) {
        classifier = "sources"
        from(sourceSets["main"].allSource.sourceDirectories.files)
    }

    publishing.publications.create<MavenPublication>("BintrayRelease") {
        from(components["java"])
        artifact(sourcesJar)
        groupId = rootProject.group.toString()
        artifactId = "${rootProject.name}-${project.name}"
        version = rootProject.version.toString()
    }

    apply(from = "$rootDir/gradle/bintray-publish.gradle")
}
