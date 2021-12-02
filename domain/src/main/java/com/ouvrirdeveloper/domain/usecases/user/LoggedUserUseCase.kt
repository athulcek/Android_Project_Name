package com.ouvrirdeveloper.domain.usecases.user

import com.ouvrirdeveloper.domain.repositories.UserRepository
import com.ouvrirdeveloper.domain.usecases.BaseUseCase

class LoggedUserUseCase(private val userRepository: UserRepository) : BaseUseCase() {

    operator fun invoke() = userRepository.getLoggedUser()
}