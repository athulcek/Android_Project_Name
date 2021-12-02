package com.ouvrirdeveloper.beetroot.android_project_app.features.home.epoxy.viewholders

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.ouvrirdeveloper.beetroot.R
import com.ouvrirdeveloper.beetroot.android_project_app.features.home.epoxy.helper.ViewBindingEpoxyModelWithHolder
import com.ouvrirdeveloper.beetroot.databinding.EpoxyItemHeaderBinding


@EpoxyModelClass(layout = R.layout.epoxy_item_header)
abstract class HeaderViewBindingEpoxyHolder :
    ViewBindingEpoxyModelWithHolder<EpoxyItemHeaderBinding>() {
    @EpoxyAttribute
    lateinit var title: String

    @EpoxyAttribute
    lateinit var onClick: ((String) -> Unit)
    override fun EpoxyItemHeaderBinding.bind() {
        btntitle.text = this@HeaderViewBindingEpoxyHolder.title
        btntitle.setOnClickListener { onClick(title) }
    }

    override fun EpoxyItemHeaderBinding.unbind() {
        btntitle.setOnClickListener(null)
    }

}