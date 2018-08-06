import com.jfrog.bintray.gradle.BintrayExtension
import com.jfrog.bintray.gradle.tasks.BintrayUploadTask
import org.jetbrains.kotlin.gradle.dsl.Coroutines

plugins {
    id("kotlin-platform-jvm") version "1.2.60"
    id("com.jfrog.bintray")
    `maven-publish`
}

dependencies {
    expectedBy(project(":common"))
    compile(group = "org.jetbrains.kotlin", name = "kotlin-stdlib-jdk8")
    testCompile(group = "org.jetbrains.kotlin", name = "kotlin-reflect")
    testCompile(group = "io.kotlintest", name = "kotlintest", version = "2.0.7")
}

kotlin {
    experimental.coroutines = Coroutines.ENABLE
}

val sourcesJar by tasks.creating(Jar::class) {
    classifier = "sources"
    from(java.sourceSets["main"].allJava.sourceDirectories.files)
}

bintray {
    user = "serebit"
    key = System.getenv("BINTRAY_KEY")
    setPublications("BintrayRelease")
    pkg(delegateClosureOf<BintrayExtension.PackageConfig> {
        repo = "public"
        name = "${rootProject.name}-${project.name}"
        version.name = rootProject.version.toString()
    })
}

publishing {
    publications.invoke {
        "BintrayRelease"(MavenPublication::class) {
            from(components["java"])
            artifact(sourcesJar)
            groupId = rootProject.group.toString()
            artifactId = "${rootProject.name}-${project.name}"
            version = rootProject.version.toString()
        }
    }
}
