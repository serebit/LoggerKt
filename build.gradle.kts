plugins {
    id("com.github.ben-manes.versions") version "0.20.0"
    id("io.gitlab.arturbosch.detekt") version "1.0.0.RC7-3"
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

detekt {
    defaultProfile(Action {
        filters = ".*test.*,.*/resources/.*,.*/tmp/.*,.*.gradle.kts"
    })
}
