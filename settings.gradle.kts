dependencyResolutionManagement {
    versionCatalogs {

    }
    repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Android_Project_Name"
include (":app")

include(":domain")
include(":data")
include(":core")
