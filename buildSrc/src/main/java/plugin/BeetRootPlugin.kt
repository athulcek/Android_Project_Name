package plugin

import AppConfig
import Versions
import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.DefaultConfig
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.provider.Property
import java.io.File

abstract class BeetRootPluginExtension {
    abstract val message: Property<String>

    init {
        message.convention("Hello from GreetingPlugin")
    }
}

class BeetRootPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val androidExtensions = project.extensions.getByName("android")
        if (androidExtensions is BaseExtension) {
            addProjectSettings(androidExtensions)
            setDefaultConfig(androidExtensions.defaultConfig)
            when (androidExtensions) {
                is LibraryExtension -> {
                    setConfigForLibraries(androidExtensions)
                }
                is AppExtension -> {
                    setSiginConfigs(androidExtensions)
                    setbuildTypes(androidExtensions)
                    setConfigForApp(androidExtensions)
                    setproductFlavors(androidExtensions)
                }
            }

        }
    }

    private fun setSiginConfigs(androidExtensions: AppExtension) {
        androidExtensions.apply {
            AppConfig.storeCredentials.forEach {
                if (it.signingConfigName.isEmpty()) {
                    println("signingConfigName cannot be empty please add a Name at AppConfig.storeCredentials")
                }
                signingConfigs.create(it.signingConfigName) {
                    storeFile = File(it.storeFile)
                    storePassword = it.storePassword
                    keyAlias = it.keyAlias
                    keyPassword = it.keyPassword
                    storeType = it.storeType
                }
            }
        }
    }

    private fun setDefaultConfig(config: DefaultConfig) {
        config.apply {
            minSdk = AppConfig.minSdk
            targetSdk = AppConfig.targetSdk
            versionCode = AppConfig.versionCode
            versionName = AppConfig.versionName
            testInstrumentationRunner = AppConfig.testInstrumentationRunner
            multiDexEnabled = AppConfig.multiDexEnabled
            vectorDrawables {
                useSupportLibrary = AppConfig.useSupportLibrary
            }
            AppConfig.defaultConfig.forEach { config ->
                buildConfigField(config.type, config.label, "${config.value}")
            }
        }
    }

    private fun addProjectSettings(androidExtensions: BaseExtension) {
        androidExtensions.apply {
            compileSdkVersion(AppConfig.compileSdk)
            buildToolsVersion(AppConfig.buildTool)
            compileOptions {
                sourceCompatibility = AppConfig.sourceCompatibility
                targetCompatibility = AppConfig.targetCompatibility
            }
            testOptions {
                unitTests.isReturnDefaultValues = true
            }
            packagingOptions {
                AppConfig.excludes.map {
                    excludes.add(it)
                }
                AppConfig.resourcesExcludes.map {
                    resources.excludes.add(it)
                }

            }
            viewBinding.isEnabled = true
            dataBinding.isEnabled = true

        }
    }

    private fun setbuildTypes(appExtension: AppExtension) {
        appExtension.apply {
            buildTypes {
                buildFeatures.compose = AppConfig.useCompose
                if (AppConfig.useCompose) {
                    composeOptions.kotlinCompilerExtensionVersion = Versions.COMPOSE
                    composeOptions.useLiveLiterals = AppConfig.useCompose
                }
                AppConfig.buildTypes.forEach { config ->
                    getByName(config.buildType) {
                        if (AppConfig.storeCredentials.map { it.signingConfigName }
                                .contains(config.signingConfigName)) {
                            signingConfig = signingConfigs.getByName(config.signingConfigName)
                        }
                        isDebuggable = config.isDebuggable
                        versionNameSuffix = config.versionNameSuffix
                        isMinifyEnabled = config.isMinifyEnabled
                        isShrinkResources = config.isShrinkResources
                        config.manifestPlaceholders.forEach { holder ->
                            manifestPlaceholders[holder.label] = holder.value
                        }
                        config.configFields.forEach { beetrootConfigs ->
                            buildConfigField(
                                beetrootConfigs.type,
                                beetrootConfigs.label,
                                "\"${beetrootConfigs.value}\""
                            )
                        }
                    }
                }
            }
        }
    }

    private fun setConfigForApp(appExtension: AppExtension) {
        appExtension.apply {
            defaultConfig {
                consumerProguardFiles("consumer-rules.pro")
            }
        }
    }

    private fun setConfigForLibraries(libraryExtension: BaseExtension) {
        libraryExtension.apply {
            defaultConfig {
                consumerProguardFiles("consumer-rules.pro")
            }
            setproductFlavors(this)
        }
    }

    private fun setproductFlavors(libraryExtension: BaseExtension) {
        libraryExtension.apply {
            productFlavors {
                AppConfig.flavours.forEach { flavour ->
                    flavorDimensions(flavour.flavorDimensions)
                    create(flavour.label) {
                        flavour.buildConfigFields.forEach { beetrootConfigs ->
                            buildConfigField(
                                beetrootConfigs.type,
                                beetrootConfigs.label,
                                "${beetrootConfigs.value}"
                            )
                        }
                    }
                }
            }
        }
    }
}