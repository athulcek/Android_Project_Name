import core.*
import org.gradle.api.JavaVersion

object AppConfig {
    val compileSdk = 31
    val buildTool = "30.0.3"
    val applicationId = "com.ouvrirdeveloper.beetroot.android_project_name"
    val minSdk = 21
    val targetSdk = 31
    val versionCode = 1
    val versionName = "1.0"
    val multiDexEnabled = true
    val useSupportLibrary = true
    val sourceCompatibility = JavaVersion.VERSION_11
    val targetCompatibility = JavaVersion.VERSION_11
    val useCompose = true
    val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    val excludes = listOf<String>(
        "META-INF/DEPENDENCIES",
        "META-INF/*.kotlin_module",
        "META-INF/{AL2.0,LGPL2.1}"
    )
    val resourcesExcludes = listOf<String>("DebugProbesKt.bin")

    val defaultConfig = listOf<BuildConfigField>(
        BuildConfigField("int", "VERSION_CODE", versionCode),
        BuildConfigField("Double", "VERSION_NAME", versionName)
    )

    val storeCredentials = listOf(
        //add as many keys you need and map with signingConfigName to the build type
        StoreCredentials(
            "releaseKey",
            //keep you key in app folder
            storeFile = "Android_Project_App_Release_Key.jks",
            storePassword = "App#$10937834523",
            keyAlias = "App",
            keyPassword = "App#$10937834523",
            storeType = "jks"
        )
    )


    val buildTypes = listOf<AppBuildConfig>(
        AppBuildConfig(
            buildType = "release",
            isMinifyEnabled = true,
            isShrinkResources = true,
            isDebuggable = false,
//            signingConfigName = "Android_Project_App_Release_Key",
            manifestPlaceholders = listOf(
                ManifestPlaceholders(
                    label = "crashlyticsCollectionEnabled",
                    value = "true"
                )
            ),
            configFields = listOf()
        ),
        AppBuildConfig(
            buildType = "debug",
//            signingConfigName = "Android_Project_App_Debug_Key",
            isMinifyEnabled = false,
            isShrinkResources = false,
            isDebuggable = true,
            versionNameSuffix = "debug",
            manifestPlaceholders = listOf(
                ManifestPlaceholders(
                    label = "crashlyticsCollectionEnabled",
                    value = "true"
                )
            ),
            configFields = listOf()
        )
    )

    val flavours = listOf<AppBuildFlavours>(
        AppBuildFlavours(
            "production",
            "environment",
            ".pro",
            listOf(
                BuildConfigField("boolean", "IS_FILE_LOG_ENABLED", false),
                BuildConfigField("String", "API_BASE_URL", "\"http://83.111.85.108/api/\"")
            )
        ),
        AppBuildFlavours(
            "staging",
            "environment",
            ".stage",
            listOf(
                BuildConfigField("boolean", "IS_FILE_LOG_ENABLED", false),
                BuildConfigField("String", "API_BASE_URL", "\"http://83.111.85.108/api/\"")
            )
        ),
        AppBuildFlavours(
            "development",
            "environment",
            ".dev",
            listOf(
                BuildConfigField("boolean", "IS_FILE_LOG_ENABLED", false),
                BuildConfigField("String", "API_BASE_URL", "\"http://83.111.85.108/api/\"")
            )
        )
    )


}
