package com.abocha.epamtelescope.usecases.recoverypassword

import com.abocha.epamtelescope.repository.RecoveryPasswordRepository
import io.reactivex.Completable
import javax.inject.Inject

class RecoveryChangePasswordUseCase @Inject constructor(
    private val recoveryPasswordRepository: RecoveryPasswordRepository
) {

    fun execute(
        recoveryPasswordToken: String,
        newPassword: String,
        confirmationPassword: String
    ): Completable =
        recoveryPasswordRepository.changePassword(
            recoveryToken = recoveryPasswordToken,
            newPassword = newPassword,
            confirmPassword = confirmationPassword
        )
}
