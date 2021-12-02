package core


data class AppBuildFlavours(
    val label: String,
    val flavorDimensions: String,
    val versionNameSuffix: String = "",
    val buildConfigFields: List<BuildConfigField>
)

data class BuildConfigField(val type: String, val label: String, val value: Any)

data class AppBuildConfig(
    val buildType: String,
    val signingConfigName: String = "debug",
    val isMinifyEnabled: Boolean,
    val isShrinkResources: Boolean,
    val isDebuggable: Boolean,
    val versionNameSuffix: String = "",
    val manifestPlaceholders: List<ManifestPlaceholders>,
    val configFields: List<BuildConfigField>
)

data class StoreCredentials(
    val signingConfigName: String,
   // var storeCredentialCreateType: StoreCredentialCreateType = StoreCredentialCreateType.create,
    val storeFile: String,
    val storePassword: String,
    val keyAlias: String,
    val keyPassword: String,
    val storeType: String
)

data class ManifestPlaceholders(val label: String, val value: String)
/*

sealed class StoreCredentialCreateType {
    object create : StoreCredentialCreateType()
    object getByName : StoreCredentialCreateType()
}*/
