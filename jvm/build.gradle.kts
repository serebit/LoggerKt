import com.jfrog.bintray.gradle.BintrayExtension
import com.jfrog.bintray.gradle.tasks.BintrayUploadTask
import org.jetbrains.kotlin.gradle.dsl.Coroutines

plugins {
    id("kotlin-platform-jvm") version "1.2.70"
    id("com.jfrog.bintray") version "1.8.4"
    `maven-publish`
}

dependencies {
    expectedBy(project(":common"))
    compile(kotlin("stdlib-jdk8"))
    testCompile(group = "io.kotlintest", name = "kotlintest-runner-junit5", version = "3.1.10")
}

kotlin.experimental.coroutines = Coroutines.ENABLE

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

tasks.getByName("bintrayUpload").doFirst { require(System.getenv("BINTRAY_KEY").isNotBlank()) }

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
