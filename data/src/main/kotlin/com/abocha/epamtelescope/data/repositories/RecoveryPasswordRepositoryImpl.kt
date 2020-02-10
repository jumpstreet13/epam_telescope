package com.abocha.epamtelescope.data.repositories

import com.abocha.epamtelescope.data.network.models.requests.ConfirmEmailRequest
import com.abocha.epamtelescope.data.network.models.requests.EmailRequest
import com.abocha.epamtelescope.data.network.models.requests.FinishRecoveryPasswordRequest
import com.abocha.epamtelescope.data.network.services.RecoveryPasswordService
import com.abocha.epamtelescope.repository.RecoveryPasswordRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class RecoveryPasswordRepositoryImpl @Inject constructor(
    private val recoveryPasswordService: RecoveryPasswordService
) : RecoveryPasswordRepository {

    override fun requestCode(email: String): Completable =
        recoveryPasswordService.requestCode(EmailRequest(email))

    override fun confirmCode(email: String, code: String): Single<String> =
        recoveryPasswordService.confirmCode(ConfirmEmailRequest(email, code))
            .map { it.recoveryToken }

    override fun changePassword(
        recoveryToken: String,
        newPassword: String,
        confirmPassword: String
    ): Completable =
        recoveryPasswordService.finishPassword(
            FinishRecoveryPasswordRequest(
                recoveryToken = recoveryToken,
                newPassword = newPassword,
                confirmationPassword = confirmPassword
            )
        )

}
