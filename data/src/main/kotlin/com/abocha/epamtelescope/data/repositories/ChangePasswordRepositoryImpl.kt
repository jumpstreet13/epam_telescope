package com.abocha.epamtelescope.data.repositories

import com.abocha.epamtelescope.data.network.models.requests.ConfirmCodeRequest
import com.abocha.epamtelescope.data.network.models.requests.FinishChangePasswordRequest
import com.abocha.epamtelescope.data.network.services.ChangePasswordService
import com.abocha.epamtelescope.repository.ChangePasswordRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class ChangePasswordRepositoryImpl @Inject constructor(
    private val changePasswordService: ChangePasswordService
) : ChangePasswordRepository {

    override fun requestCode(): Completable =
        changePasswordService.requestCode()

    override fun confirmCode(code: String): Single<String> =
        changePasswordService.confirmCode(ConfirmCodeRequest(code))
            .map { it.changePasswordToken }

    override fun changePassword(
        changePasswordToken: String,
        oldPassword: String,
        newPassword: String,
        confirmPassword: String
    ): Completable =
        changePasswordService.finish(
            FinishChangePasswordRequest(
                changePasswordToken = changePasswordToken,
                oldPassword = oldPassword,
                newPassword = newPassword,
                confirmationPassword = confirmPassword
            )
        )

}
