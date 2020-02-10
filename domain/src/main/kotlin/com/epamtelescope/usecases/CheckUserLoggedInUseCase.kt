package com.abocha.epamtelescope.usecases

import com.abocha.epamtelescope.exceptions.UserNotAuthorizedException
import com.abocha.epamtelescope.repository.AuthRepository
import io.reactivex.Completable
import javax.inject.Inject

/**
 * Created by Oleg Sheliakin on 2020-01-13.
 * Contact me by email - olegsheliakin@gmail.com
 */
class CheckUserLoggedInUseCase @Inject constructor(private val authRepository: AuthRepository) {

    fun execute(): Completable =
        Completable.fromAction {
            if (!authRepository.isLoggedIn()) {
                throw UserNotAuthorizedException
            }
        }
}
