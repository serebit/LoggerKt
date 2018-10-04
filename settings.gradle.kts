rootProject.name = "loggerkt"
enableFeaturePreview("STABLE_PUBLISHING")

include(":common", ":jvm", ":native")

pluginManagement {
    resolutionStrategy {
        eachPlugin {
            when {
                requested.id.id.startsWith("kotlin-platform-") ->
                    useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:${requested.version}")
                requested.id.id == "konan" ->
                    useModule("org.jetbrains.kotlin:kotlin-native-gradle-plugin:${requested.version}")
            }
        }
    }
}
