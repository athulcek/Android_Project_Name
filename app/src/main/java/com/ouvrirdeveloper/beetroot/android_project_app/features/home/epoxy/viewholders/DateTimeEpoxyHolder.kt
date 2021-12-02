package com.ouvrirdeveloper.beetroot.android_project_app.features.home.epoxy.viewholders

import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.ouvrirdeveloper.beetroot.R
import com.ouvrirdeveloper.beetroot.android_project_app.features.home.epoxy.helper.ViewBindingEpoxyModelWithHolder
import com.ouvrirdeveloper.beetroot.databinding.EpoxyItemDateTimeBinding
import com.ouvrirdeveloper.core.extensions.gone
import com.ouvrirdeveloper.core.extensions.makeVisibile


@EpoxyModelClass(layout = R.layout.epoxy_item_date_time)
abstract class DateTimeEpoxyHolder :
    ViewBindingEpoxyModelWithHolder<EpoxyItemDateTimeBinding>() {

    @EpoxyAttribute
    var canAnalog: Boolean = false

    @EpoxyAttribute
    lateinit var onClick: ((Any) -> Unit)

    override fun EpoxyItemDateTimeBinding.bind() {
        if (canAnalog) {
            clockAnalog.makeVisibile()
            clockDigital.gone()
        } else {
            clockAnalog.gone()
            clockDigital.makeVisibile()
        }
        clockAnalog.setOnClickListener { onClick("") }
        clockDigital.setOnClickListener { onClick("") }
    }

    override fun EpoxyItemDateTimeBinding.unbind() {
        clockDigital.setOnClickListener(null)
        clockAnalog.setOnClickListener(null)
    }

}