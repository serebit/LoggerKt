rootProject.name = "logkat"
enableFeaturePreview("STABLE_PUBLISHING")

include(":common", ":jvm")

pluginManagement {
    resolutionStrategy {
        eachPlugin {
            when {
                requested.id.id.startsWith("kotlin-platform-") ->
                    useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:${requested.version}")
            }
        }
    }
}
