package com.ouvrirdeveloper.beetroot.android_project_app.features.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ouvrirdeveloper.beetroot.R
import com.ouvrirdeveloper.beetroot.android_project_app.features.splash.LandingPageActivity
import com.ouvrirdeveloper.core.extensions.launchActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launchActivity<LandingPageActivity>()
//        launchActivity<HomeActivity>()
        finish()
    }
}