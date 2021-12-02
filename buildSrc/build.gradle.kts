plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()
    maven(uri("https://jitpack.io"))
}
gradlePlugin {
    plugins {
        register("BeetRootPlugin") {
            id = "BeetRootPlugin"
            implementationClass = "plugin.BeetRootPlugin"
        }
    }
}
dependencies {
    val nav_version = "2.3.5"
    implementation("com.android.tools.build:gradle:7.0.3")
    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
    implementation("com.google.firebase:firebase-crashlytics-gradle:2.8.0")
    implementation("com.google.gms:google-services:4.3.10")
    /* Example Dependency */
    /* Depend on the kotlin plugin, since we want to access it in our plugin */
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
    /* Depend on the default Gradle API's since we want to build a custom plugin */
    implementation(gradleApi())
    implementation(localGroovy())
}
