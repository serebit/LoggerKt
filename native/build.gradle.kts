plugins {
    id("konan") version "0.9.2"
}

dependencies {
    expectedBy(project(":common"))
}

konan.targets = mutableListOf("macos_x64", "linux_x64", "mingw_x64")

konanArtifacts.library("${rootProject.name}-${project.name}", Action {
    enableMultiplatform(true)
    srcFiles("$projectDir/src/main/kotlin")
})
