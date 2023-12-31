@file:Suppress("UnstableApiUsage")

include(":testing")

include(":database")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Tourist News"
include(":app")
include(":data")
include(":presentation")
include(":domain")
include(":designsystem")
