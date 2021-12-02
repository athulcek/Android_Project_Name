package com.ouvrirdeveloper.beetroot.android_project_app.application

import android.app.Application
import android.os.StrictMode
import androidx.lifecycle.MutableLiveData
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.util.CoilUtils
import com.orhanobut.logger.BuildConfig
import com.ouvrirdeveloper.basearc.helper.network.base.ConnectivityProvider
import okhttp3.OkHttpClient

class App : Application(), ImageLoaderFactory, ConnectivityProvider.ConnectivityStateListener {

    companion object {
        lateinit var application: App
        const val PREF_NAME = "PREF_NAME"
    }

    private val provider: ConnectivityProvider by lazy { ConnectivityProvider.createProvider(this) }

    var isNetworkAvailable = MutableLiveData<Boolean>()

    override fun onCreate() {
        application = this
        super.onCreate()
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()
                    .penaltyLog()

                    .penaltyDropBox()
                    .build()
            )
            StrictMode.setVmPolicy(
                StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects()
                    .penaltyLog()

                    .penaltyDropBox()
                    .build()
            )
        }

        provider.addListener(this)
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(applicationContext)
            .allowHardware(false)
            .okHttpClient {
                OkHttpClient.Builder()
                    .cache(CoilUtils.createDefaultCache(applicationContext))
                    .build()
            }
            .build()
    }

    override fun onStateChange(state: ConnectivityProvider.NetworkState) {
        when (state) {
            is ConnectivityProvider.NetworkState.ConnectedState.Connected,
            is ConnectivityProvider.NetworkState.ConnectedState.ConnectedLegacy -> isNetworkAvailable.value =
                true
            ConnectivityProvider.NetworkState.NotConnectedState -> isNetworkAvailable.value = false
        }
    }
}