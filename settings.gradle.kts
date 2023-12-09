pluginManagement {
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
        maven("https://storage.googleapis.com/download.flutter.io")
        maven("https://maven.pkg.github.com/coindcx-okto/okto-mobile-sdk") {
            credentials {
                username = "oktoWallet"
                password = "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";
            }
        }
    }
}

rootProject.name = "Tracto"
include(":app")
 