package com.ouvrirdeveloper.beetroot.android_project_app.di

import com.ouvrirdeveloper.beetroot.android_project_app.features.home.HomeViewModel
import com.ouvrirdeveloper.beetroot.android_project_app.features.sign_in.SignInViewModel
import com.ouvrirdeveloper.beetroot.android_project_app.features.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val ViewModelModule = module {

    viewModel { HomeViewModel(get()) }
    viewModel { SignInViewModel(get()) }
    viewModel { SplashViewModel(get()) }


    //viewModel { (valueFromActivity: String) -> HomeViewModel(valueFromActivity) }
}


val CommonModule = module {
    // single { createUnityPlayer(get()) }
//    single { PrefUtil.init(get()) }
}
