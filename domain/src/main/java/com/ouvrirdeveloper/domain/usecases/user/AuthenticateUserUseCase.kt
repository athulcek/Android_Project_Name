package com.ouvrirdeveloper.domain.usecases.user

import com.ouvrirdeveloper.domain.helpers.extensions.isNumeric
import com.ouvrirdeveloper.domain.models.common.NullorEmpty
import com.ouvrirdeveloper.domain.models.common.Resource
import com.ouvrirdeveloper.domain.repositories.UserRepository
import com.ouvrirdeveloper.domain.state.NoUserFound
import com.ouvrirdeveloper.domain.usecases.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthenticateUserUseCase(private val userRepository: UserRepository) : BaseUseCase() {

    operator fun invoke(pin: String?): Flow<Resource<Boolean>> {
        return flow {
            emit(Resource.Loading())
            when {
                pin.isNullOrEmpty() -> emit(Resource.Error("No User Found", null, NullorEmpty))
                pin.isNumeric() -> userRepository.authenticate(pin.toInt())
                else -> emit(Resource.Error("No User Found", null, NoUserFound))
            }

        }

    }
}