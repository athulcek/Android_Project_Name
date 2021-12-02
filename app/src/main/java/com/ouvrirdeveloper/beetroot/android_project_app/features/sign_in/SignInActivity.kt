package com.ouvrirdeveloper.beetroot.android_project_app.features.sign_in

import android.os.Bundle
import com.ouvrirdeveloper.basearc.ui.base.BaseActivityWithBinding
import com.ouvrirdeveloper.beetroot.R
import com.ouvrirdeveloper.beetroot.android_project_app.features.base.setDisplayHomeEnabled
import com.ouvrirdeveloper.beetroot.android_project_app.features.base.setToolbarTitle
import com.ouvrirdeveloper.beetroot.android_project_app.features.home.HomeActivity
import com.ouvrirdeveloper.beetroot.databinding.ActivitySignInBinding
import com.ouvrirdeveloper.core.constants.AppConstant
import com.ouvrirdeveloper.core.extensions.launchActivityAsRoot
import com.ouvrirdeveloper.domain.models.common.NullorEmpty
import com.ouvrirdeveloper.domain.models.common.Resource
import org.koin.android.ext.android.inject


class SignInActivity : BaseActivityWithBinding<ActivitySignInBinding>(R.layout.activity_sign_in) {

    private val viewModel: SignInViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.let {
            it.viewModel = viewModel
        }
//         setToolbar()
        setToolbarTitle(getString(R.string.land_sign_in))
        setDisplayHomeEnabled(intent.getBooleanExtra(AppConstant.KEY_SHOW_BACK_BUTTON, false))
        setClickListeners()
        setupObservers()

    }

    private fun setClickListeners() {

    }

    override fun onResume() {
        super.onResume()
        binding.scrollView.scrollTo(0, 0)
    }

    private fun setupObservers() {
        viewModel.loginDetail.observe(this, {
            when (it) {
                is Resource.Error -> {
                    when (it.erroStatus) {
                        NullorEmpty -> {}
                    }
                }
                is Resource.Loading -> showProgress()
                is Resource.Success -> {}
            }
        })


    }

    private fun onAuthenticateSuccess() {
        launchActivityAsRoot<HomeActivity> { }
    }
}