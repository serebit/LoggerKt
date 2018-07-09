rootProject.name = "loggerkt"
enableFeaturePreview("STABLE_PUBLISHING")

include(":common")
include(":jvm")

pluginManagement {
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id.startsWith("kotlin-platform-")) {
                useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:${requested.version}")
            }
        }
    }
}
