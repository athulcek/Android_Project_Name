package com.ouvrirdeveloper.domain.di

import com.ouvrirdeveloper.domain.repositories.UserRepository
import com.ouvrirdeveloper.domain.usecases.UserUseCase
import com.ouvrirdeveloper.domain.usecases.user.AuthenticateUserUseCase
import com.ouvrirdeveloper.domain.usecases.user.LoggedUserUseCase
import com.ouvrirdeveloper.domain.usecases.user.LogoutUserUseCase
import org.koin.dsl.module

val DomainProviderModule = module {

    single { createUserUsecase(get(), get(), get()) }
    single { createAuthenticateUserUseCase(get()) }
    single { createLoggedUserUseCase(get()) }
    single { createLogoutUserUseCase(get()) }
}

fun createAuthenticateUserUseCase(userRepository: UserRepository) =
    AuthenticateUserUseCase(userRepository)

fun createLoggedUserUseCase(userRepository: UserRepository) = LoggedUserUseCase(userRepository)
fun createLogoutUserUseCase(userRepository: UserRepository) = LogoutUserUseCase(userRepository)

fun createUserUsecase(
    authenticateUserUseCase: AuthenticateUserUseCase,
    getLoggedUserUseCase: LoggedUserUseCase,
    logoutUserUseCase: LogoutUserUseCase
) = UserUseCase(
    authenticateUserUseCase,
    getLoggedUserUseCase,
    logoutUserUseCase
)

