package com.abocha.epamtelescope.usecases.recoverypassword

import com.abocha.epamtelescope.repository.RecoveryPasswordRepository
import io.reactivex.Single
import javax.inject.Inject

class ConfirmRecoveryCodeUseCase @Inject constructor(
    private val recoveryPasswordRepository: RecoveryPasswordRepository
) {

    fun execute(email: String, code: String): Single<String> =
        recoveryPasswordRepository.confirmCode(email, code)
}
