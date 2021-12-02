import config.addDomain

plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinKapt)
    id(Plugins.kotlinAndroid)
    id(Plugins.BeetRootPlugin)
}
addMoshi()
addKotlin()
addKoin()
addCoil()
addDomain()
addMaterial()
addNavigation()
addFragment()
addTimber()
addLogger()
addRoom()
addPaging()
addLottie()