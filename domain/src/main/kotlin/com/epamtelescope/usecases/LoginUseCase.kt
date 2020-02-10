package com.abocha.epamtelescope.usecases

import com.abocha.epamtelescope.repository.AuthRepository
import io.reactivex.Completable
import javax.inject.Inject

/**
 * Created by Oleg Sheliakin on 2019-12-23.
 * Contact me by email - olegsheliakin@gmail.com
 */
class LoginUseCase @Inject constructor(private val authRepository: AuthRepository) {

    fun execute(email: String, password: String): Completable =
        authRepository.login(email, password)
}
