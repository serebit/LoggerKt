import com.jfrog.bintray.gradle.tasks.BintrayUploadTask

plugins {
    id("com.github.ben-manes.versions") version "0.20.0"
    id("io.gitlab.arturbosch.detekt") version "1.0.0.RC8" apply false
    id("com.jfrog.bintray") version "1.8.4" apply false
    `maven-publish`
}

group = "com.serebit"
version = "0.4.0"

repositories {
    jcenter()
}

subprojects {
    repositories {
        jcenter()
    }
}

tasks {
    withType<BintrayUploadTask> {
        dependsOn("build")
    }
}
