package com.ouvrirdeveloper.beetroot.android_project_app.application.initializers

import android.content.Context
import androidx.startup.Initializer
import com.ouvrirdeveloper.beetroot.android_project_app.di.CommonModule
import com.ouvrirdeveloper.beetroot.android_project_app.di.ViewModelModule
import com.ouvrirdeveloper.core.di.CoreProviderModule
import com.ouvrirdeveloper.data.di.DataProviderModule
import com.ouvrirdeveloper.data.di.NetworkModule
import com.ouvrirdeveloper.domain.di.DomainProviderModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

class KoinInitializer : Initializer<KoinApplication> {
    override fun create(context: Context): KoinApplication {
        return startKoin {
            androidContext(context.applicationContext)
            modules(
                listOf(
                    ViewModelModule,
                    CommonModule,
                    CoreProviderModule,
                    DataProviderModule,
                    NetworkModule,
                    DomainProviderModule
                )
            )
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        // No dependencies on other libraries.
        return emptyList()
    }
}