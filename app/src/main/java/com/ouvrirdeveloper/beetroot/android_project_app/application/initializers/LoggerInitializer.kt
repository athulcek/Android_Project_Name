package com.ouvrirdeveloper.beetroot.android_project_app.application.initializers

import android.content.Context
import androidx.startup.Initializer
import com.orhanobut.logger.*
import com.ouvrirdeveloper.beetroot.R
import com.ouvrirdeveloper.core.extensions.AppLog
import com.ouvrirdeveloper.core.log.FileTree
import com.ouvrirdeveloper.core.log.HyperlinkedDebugTree
import com.ouvrirdeveloper.core.log.ReleaseTree
import kotlinx.coroutines.InternalCoroutinesApi
import timber.log.Timber

class LoggerInitializer : Initializer<Unit> {
    @InternalCoroutinesApi
    override fun create(context: Context): Unit {
        if (BuildConfig.DEBUG) {
            Timber.plant(HyperlinkedDebugTree(), FileTree())
            val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false) // (Optional) Whether to show thread info or not. Default true
                .methodCount(0) // (Optional) How many method line to show. Default 2
                .methodOffset(10) // (Optional) Hides internal method calls up to offset. Default 5
                .logStrategy(LogStrategy { priority, tag, message ->
                    AppLog.log(priority, tag, message)
                }) // (Optional) Changes the log strategy to print out. Default LogCat
                .tag(context.getString(R.string.app_name)) // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build()

            Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))
            //Logger.addLogAdapter(DiskLogAdapter(formatStrategy))
        } else {
            Timber.plant(ReleaseTree())
        }
        return
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        // No dependencies on other libraries.
        return listOf(KoinInitializer::class.java)
    }
}