import com.jfrog.bintray.gradle.BintrayExtension
import com.jfrog.bintray.gradle.BintrayUploadTask
import org.apache.tools.ant.types.resources.comparators.Date
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
    testCompile(group = "io.kotlintest", name = "kotlintest", version = "2.0.7")
}

bintray {
    user = System.getenv("BINTRAY_USER")
    key = System.getenv("BINTRAY_KEY")
    publish = true
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
            groupId = group.toString()
            artifactId = name
            version = version.toString()
        }
    }
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

detekt {
    profile("main", Action {
        input = "$projectDir/src/main/kotlin"
        config = "$projectDir/detekt.yml"
        filters = ".*test.*,.*/resources/.*,.*/tmp/.*"
    })
}
