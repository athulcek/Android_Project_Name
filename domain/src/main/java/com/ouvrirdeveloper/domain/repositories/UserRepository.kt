package com.ouvrirdeveloper.domain.repositories

import com.ouvrirdeveloper.domain.models.User
import com.ouvrirdeveloper.domain.models.common.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getLoggedUser(): User?
    fun authenticate(pin: Int): Flow<Resource<Boolean>>
}