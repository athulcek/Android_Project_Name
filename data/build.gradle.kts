import config.addCore
import config.addDomain

plugins {

    id(Plugins.androidLibrary)
    id(Plugins.kotlinKapt)
    id(Plugins.kotlinAndroid)
    id(Plugins.BeetRootPlugin)
}

addKoin()
addNetwork()
addCore()
addDomain()
addRoom()
addMoshi()
addAndroidxPreference()
addWork()
/*

plugins {
    id(Plugins.kotlin)
    kotlin("plugin.serialization") version Versions.KOTLIN

}

repositories {
    mavenCentral()
}

dependencies {

    implementation(Deps.KOIN_CORE)
    implementation(Deps.KOTLIN_STDLIB)
    implementation(Deps.KOTLIN_REFLECT)
    implementation(Deps.KOTLIN_COROUTINE_CORE)
    implementation(Deps.KOTLIN_SERIALIZATION)
    implementation(Deps.Ktor.KTOR_CORE)
    implementation(Deps.Ktor.KTOR_CIO)
    implementation(Deps.Ktor.KTOR_SERIALIZATION)
    implementation(Deps.Ktor.KTOR_SERIALIZATION_JVM)
    implementation(Deps.Ktor.KTOR_JSON)
    implementation(Deps.Ktor.KTOR_LOGGING )
    implementation(Deps.Ktor.KTOR_OKHTTP)
}*/
