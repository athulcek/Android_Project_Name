package com.ouvrirdeveloper.beetroot.android_project_app.features.splash

import android.os.Bundle
import android.os.SystemClock
import com.ouvrirdeveloper.basearc.core.extension.asString
import com.ouvrirdeveloper.basearc.ui.base.BaseActivity
import com.ouvrirdeveloper.beetroot.BuildConfig
import com.ouvrirdeveloper.beetroot.R
import com.ouvrirdeveloper.beetroot.android_project_app.features.home.HomeActivity
import com.ouvrirdeveloper.beetroot.android_project_app.features.sign_in.SignInActivity
import com.ouvrirdeveloper.core.extensions.launchActivity
import com.ouvrirdeveloper.core.extensions.showToast
import com.ouvrirdeveloper.core.utils.permitDiskReads
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class LandingPageActivity : BaseActivity(R.layout.activity_splash) {
    companion object {
        private const val SPLASH_TIME_OUT = 3000
    }

    private val splashViewModel: SplashViewModel by inject()
    private var lastRetryClickTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObserver()
        permitDiskReads {
            CoroutineScope(Dispatchers.IO).launch {
                if (splashViewModel.isLoggedIn()) {
                    launchAsPerLoginState()
                } else {
                    launchSignInScreen()
                }
            }
        }
    }


    private fun launchAsPerLoginState() {
        if (isNetworkAvailable) {
            splashViewModel.doLogin()
        } else {
            showToast(R.string.no_internet_connection.asString(this))
            showRetry(
                R.string.no_internet_connection.asString(this),
                {
                    if ((SystemClock.elapsedRealtime() - lastRetryClickTime) > 1500L) {
                        showProgress()
                        lastRetryClickTime = SystemClock.elapsedRealtime()
                        launchAsPerLoginState()
                    }
                },
                lottieFile = R.raw.no_internet
            )
        }
    }


    private fun launchAndFinishSignInActivity() {
        launchActivity<SignInActivity>()
        finishAfterTransition()
    }


    private fun setupObserver() {

    }


    private fun launchHomeScreen() {
        launchActivity<HomeActivity> { }
        finishAfterTransition()
    }

    private fun launchSignInScreen() {
        launchActivity<SignInActivity>()
        finishAfterTransition()
    }


}