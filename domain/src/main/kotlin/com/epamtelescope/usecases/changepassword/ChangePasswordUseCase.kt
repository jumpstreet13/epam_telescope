package com.abocha.epamtelescope.usecases.changepassword

import com.abocha.epamtelescope.repository.ChangePasswordRepository
import io.reactivex.Completable
import javax.inject.Inject

class ChangePasswordUseCase @Inject constructor(
    private val changePasswordRepository: ChangePasswordRepository
) {

    fun execute(
        changePasswordToken: String,
        oldPassword: String,
        newPassword: String,
        confirmationPassword: String
    ): Completable =
        changePasswordRepository.changePassword(
            changePasswordToken = changePasswordToken,
            oldPassword = oldPassword,
            newPassword = newPassword,
            confirmPassword = confirmationPassword
        )
}
