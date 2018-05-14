import com.jfrog.bintray.gradle.BintrayExtension
import com.jfrog.bintray.gradle.BintrayUploadTask
import org.apache.tools.ant.types.resources.comparators.Date
import org.jetbrains.dokka.DokkaConfiguration
import org.jetbrains.dokka.gradle.DokkaPlugin
import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    kotlin("jvm") version "1.2.41"
    id("org.jetbrains.dokka") version "0.9.16"
    id("com.github.ben-manes.versions") version "0.17.0"
    id("io.gitlab.arturbosch.detekt") version "1.0.0.RC7"
    id("com.jfrog.bintray") version "1.8.0"
    id("maven-publish")
}

group = "com.serebit"
version = "0.3.0"

repositories {
    jcenter()
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
    compile(kotlin("reflect"))
    compile(group = "org.jetbrains.kotlinx", name = "kotlinx-coroutines-core", version = "0.22.5")
    testCompile(group = "io.kotlintest", name = "kotlintest", version = "2.0.7")
}

tasks {
    withType<BintrayUploadTask> {
        dependsOn("build")
    }

    withType<DokkaTask> {
        outputFormat = "html"
        outputDirectory = "docs"
    }
}

val sourcesJar by tasks.creating(Jar::class) {
    classifier = "sources"
    from(java.sourceSets["main"].allJava.sourceDirectories.files)
}

bintray {
    user = System.getenv("BINTRAY_USER")
    key = System.getenv("BINTRAY_KEY")
    setPublications("BintrayRelease")
    pkg(delegateClosureOf<BintrayExtension.PackageConfig> {
        repo = "Maven"
        name = "loggerkt"
        vcsUrl = "https://github.com/serebit/LoggerKt.git"
        version.name = project.version.toString()
        setLicenses("Apache-2.0")
    })
}

publishing {
    publications.invoke {
        "BintrayRelease"(MavenPublication::class) {
            from(components["java"])
            artifact(sourcesJar)
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()
        }
    }
}

detekt {
    profile("main", Action {
        input = "$projectDir/src/main/kotlin"
        config = "$projectDir/detekt.yml"
        filters = ".*test.*,.*/resources/.*,.*/tmp/.*"
    })
}
