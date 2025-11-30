pluginManagement {
    includeBuild("build-logic")
    repositories {
        google ({
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroup("com.google.gms")
                includeGroup("com.google.firebase")
                includeGroupByRegex("androidx.*")
            }
        })
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

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
rootProject.name = "myhobbies"
include(":app")

include(":core:common")
include(":core:designsystem")
include(":core:common-ui")
include(":core:navigation")

include(":feature:home")
include(":feature:home:data")
include(":feature:home:domain")
include(":feature:home:presentation")

include(":feature:auth")
include(":feature:auth:data")
include(":feature:auth:domain")
include(":feature:auth:presentation")
