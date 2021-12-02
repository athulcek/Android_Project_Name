package com.ouvrirdeveloper.beetroot.android_project_app.features.home.epoxy.viewholders

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.ouvrirdeveloper.beetroot.R
import com.ouvrirdeveloper.beetroot.android_project_app.features.home.epoxy.helper.ViewBindingEpoxyModelWithHolder
import com.ouvrirdeveloper.beetroot.databinding.EpoxyItemAppIconBinding


@EpoxyModelClass(layout = R.layout.epoxy_item_app_icon)
abstract class AppIconViewBindingEpoxyHolder :
    ViewBindingEpoxyModelWithHolder<EpoxyItemAppIconBinding>() {
    @EpoxyAttribute
    lateinit var title: String

    @EpoxyAttribute
    var icon: Int = R.drawable.ic_add_circle

    @EpoxyAttribute
    lateinit var onClick: ((String) -> Unit)
    override fun EpoxyItemAppIconBinding.bind() {
        tvTitle.text = this@AppIconViewBindingEpoxyHolder.title
        imvIcon.setBackgroundResource(this@AppIconViewBindingEpoxyHolder.icon)
        viewClick.setOnClickListener { onClick(title) }
    }

    override fun EpoxyItemAppIconBinding.unbind() {
        viewClick.setOnClickListener(null)
    }

}