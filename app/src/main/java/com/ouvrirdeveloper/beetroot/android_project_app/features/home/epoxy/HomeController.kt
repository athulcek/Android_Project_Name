package com.ouvrirdeveloper.beetroot.android_project_app.features.home.epoxy

import android.content.Context
import com.airbnb.epoxy.EpoxyController
import com.ouvrirdeveloper.basearc.core.extension.asString
import com.ouvrirdeveloper.beetroot.R
import com.ouvrirdeveloper.beetroot.android_project_app.features.home.epoxy.viewholders.dateTimeEpoxyHolder
import com.ouvrirdeveloper.beetroot.android_project_app.features.home.epoxy.viewholders.headerViewBindingEpoxyHolder
import com.ouvrirdeveloper.beetroot.android_project_app.features.home.epoxy.views.horizontalLineraLayoutCarousel
import com.ouvrirdeveloper.domain.models.common.AppInfo


class HomeController(val context: Context, val onClick: ((String, Any) -> Unit)? = null) :
    EpoxyController() {

    var isLoading: Boolean = true
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }

    var topIcons: List<AppInfo> = emptyList()


    override fun buildModels() {
        if (topIcons.isNotEmpty()) {
            buildTopIcons()
        }

    }

    fun buildTopIcons() {

        dateTimeEpoxyHolder {
            id("date_time")
            canAnalog(false)
        }
        horizontalLineraLayoutCarousel {
            id("top_icons")
            numViewsToShowOnScreen(3f)
            models(
                DataGenerator.getAppIconEpoxyHolder(
                    this@HomeController.context,
                    this@HomeController.topIcons
                ).map {
                    it.id(it.appInfo.appName)
                }
            )
        }



        headerViewBindingEpoxyHolder {
            id("cheque_summary")
            title(R.string.app_name.asString(this@HomeController.context))
            onClick {

            }
        }
    }

    fun doForTopIcons(items: List<AppInfo>) {
        topIcons = items
        requestModelBuild()
    }
}