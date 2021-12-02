package com.ouvrirdeveloper.domain.usecases

import com.ouvrirdeveloper.domain.usecases.user.AuthenticateUserUseCase
import com.ouvrirdeveloper.domain.usecases.user.LoggedUserUseCase
import com.ouvrirdeveloper.domain.usecases.user.LogoutUserUseCase

data class UserUseCase(
    val authenticateUserUseCase: AuthenticateUserUseCase,
    val getLoggedUserUseCase: LoggedUserUseCase,
    val logoutUserUseCase: LogoutUserUseCase
) : BaseUseCase() {


}

