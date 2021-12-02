package com.ouvrirdeveloper.beetroot.android_project_app.features.home

import com.ouvrirdeveloper.basearc.ui.base.BaseActivityWithBinding
import com.ouvrirdeveloper.beetroot.R
import com.ouvrirdeveloper.beetroot.databinding.ActivityHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivityWithBinding<ActivityHomeBinding>(R.layout.activity_home) {
    private val viewmodel by viewModel<HomeViewModel>()

}