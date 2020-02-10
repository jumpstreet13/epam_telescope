package com.abocha.epamtelescope.usecases.recoverypassword

import com.abocha.epamtelescope.repository.RecoveryPasswordRepository
import io.reactivex.Completable
import javax.inject.Inject

class SendRecoveryCodeUseCase @Inject constructor(private val recoveryPasswordRepository: RecoveryPasswordRepository) {

    fun execute(email: String): Completable =
        recoveryPasswordRepository.requestCode(email)
}
