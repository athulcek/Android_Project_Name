package com.ouvrirdeveloper.beetroot.android_project_app.features.sign_in

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ouvrirdeveloper.basearc.ui.base.BaseViewModel
import com.ouvrirdeveloper.core.utils.permitDiskReads
import com.ouvrirdeveloper.domain.models.User
import com.ouvrirdeveloper.domain.models.common.Resource
import com.ouvrirdeveloper.domain.usecases.UserUseCase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay

class SignInViewModel(
    private val userUseCase: UserUseCase
) : BaseViewModel() {

    companion object {
        const val ignoreValidationMessage = -1
        const val EmailRequired = 1
        const val EmailInvalid = 2
        const val PasswordRequired = 3
    }

    val pin = MutableLiveData<String>()


    val isKeepMeLoggedIn = MutableLiveData(userUseCase.getLoggedUserUseCase())

    private val _loginDetail = MutableLiveData<Resource<User?>>()
    val loginDetail: LiveData<Resource<User?>>
        get() = _loginDetail


    private val _validation = MutableLiveData<Int>()
    val validation: LiveData<Int>
        get() = _validation


    init {
        permitDiskReads {
            runAsyncTask {
                delay(1000)
                val user = userUseCase.getLoggedUserUseCase()
                if (user != null) {

                } else {


                }
            }
        }
    }


    @InternalCoroutinesApi
    @Suppress("EXPERIMENTAL_API_USAGE")
    fun doLogIn() {
        runIfNotInProgress {
            runOnMain {
                userUseCase.authenticateUserUseCase(
                    pin = pin.value
                )

            }
        }
    }


}