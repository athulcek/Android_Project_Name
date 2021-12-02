import config.addCore
import config.addData
import config.addDomain

plugins {
    id(Plugins.application)
    id(Plugins.kotlinKapt)
    id(Plugins.kotlinAndroid)
    id(Plugins.navigationSafeargsKotlin)
    // id(Plugins.gmsGoogleServices)
    //id(Plugins.firebaseCrashlytics)
    id(Plugins.BeetRootPlugin)

}

android {
    
    defaultConfig {
        applicationId = AppConfig.applicationId
    }
}

addCompose()
addKoin()
addKotlin()
addAndroid()
addMaterial()
addDebug()
addCore()
addCoil()
addDomain()
addData()
addLottie()
addEpoxyRecyclerView()
