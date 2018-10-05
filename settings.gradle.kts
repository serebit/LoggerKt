rootProject.name = "logkat"
enableFeaturePreview("STABLE_PUBLISHING")
enableFeaturePreview("GRADLE_METADATA")

include(":common", ":jvm")

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
    }
}
