package com.ouvrirdeveloper.beetroot.android_project_app.features.home.epoxy.viewholders

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import com.ouvrirdeveloper.beetroot.R
import com.ouvrirdeveloper.domain.models.common.AppInfo
import com.ouvrirdeveloper.beetroot.android_project_app.viewbinding.ViewBindingKotlinModel
import com.ouvrirdeveloper.beetroot.databinding.EpoxyItemAppIconBinding
import com.ouvrirdeveloper.core.extensions.setOnclickCombained

data class AppIconEpoxyHolder(
    val appInfo: AppInfo,
    val context: Context
) : ViewBindingKotlinModel<EpoxyItemAppIconBinding>(R.layout.epoxy_item_app_icon) {
    override fun EpoxyItemAppIconBinding.bind() {
        tvTitle.text = appInfo.appName
        imvIcon.setBackgroundResource(appInfo.icon)
        setOnclickCombained(arrayOf(viewClick)) {
            appInfo.onClick.invoke(appInfo)
        }
    }


    private fun computeCardHeight(context: Context, width: Int): Int {
        val fakeViewBinding: EpoxyItemAppIconBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.epoxy_item_app_icon,
                null,
                false
            )
//        fakeViewBinding.setVariable(BR.data, FakeViewData.MAX_ALL.itemViewData)
        fakeViewBinding.getRoot()
            .measure(View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY), 0)
        return fakeViewBinding.getRoot().getMeasuredHeight()
    }
}
