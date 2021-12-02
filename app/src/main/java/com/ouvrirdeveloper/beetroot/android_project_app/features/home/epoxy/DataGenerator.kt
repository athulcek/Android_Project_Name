package com.ouvrirdeveloper.beetroot.android_project_app.features.home.epoxy

import android.content.Context
import com.ouvrirdeveloper.beetroot.android_project_app.features.home.epoxy.viewholders.AppIconEpoxyHolder
import com.ouvrirdeveloper.domain.models.common.AppInfo

object DataGenerator {




    fun getAppIconEpoxyHolder(
        context: Context,
        pending: List<AppInfo>
    ): List<AppIconEpoxyHolder> {
        val items = mutableListOf<AppIconEpoxyHolder>()
        for (item in pending) {
            val kk = AppIconEpoxyHolder(
                appInfo = item,
                context = context
            )

            items.add(kk)
        }
        return items

    }
}