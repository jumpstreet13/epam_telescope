package com.abocha.epamtelescope.usecases

import com.abocha.epamtelescope.repository.AuthRepository
import io.reactivex.Completable
import javax.inject.Inject

class LogoutUseCase @Inject constructor(private val authRepository: AuthRepository) {

    fun logout(): Completable = authRepository.logout()

}
