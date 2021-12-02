package com.ouvrirdeveloper.beetroot.android_project_app.application.initializers

import android.content.Context
import androidx.startup.Initializer
import com.ouvrirdeveloper.beetroot.android_project_app.application.App
import com.ouvrirdeveloper.core.utils.PrefUtil

class OtherInitializer : Initializer<Unit> {
    override fun create(context: Context): Unit {

        try {
            PrefUtil.init(context.applicationContext, App.PREF_NAME)
        } catch (e: Exception) {
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {

        return listOf(
            KoinInitializer::class.java,
            LoggerInitializer::class.java
        )
    }
}