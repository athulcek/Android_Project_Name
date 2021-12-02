package com.ouvrirdeveloper.beetroot.android_project_app.features.home.epoxy.views

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.ModelView


@ModelView(autoLayout = ModelView.Size.WRAP_WIDTH_WRAP_HEIGHT, saveViewState = true)
class HorizontalLineraLayoutCarousel(context: Context) : Carousel(context) {

    init {
        //setBackgroundColor(R.color.secondaryColor.asColor(context))
    }


    var spanSize: Int = 3


    override fun createLayoutManager(): LayoutManager {
        return LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//        return GridLayoutManager(context, spanSize, GridLayoutManager.VERTICAL, false)
    }

}

/*
// usage in fragment
verticalGridCarousel {
    id("Carousel")
    models(texts.map { text ->
        CustomTextModel_().apply {
            id(text)
            styleBuilder {
                it.text(text)
                it.textSizeDp(30)
                it.layoutWidth(ViewGroup.LayoutParams.MATCH_PARENT)
                it.layoutHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
            }
        }
    })
}*/
