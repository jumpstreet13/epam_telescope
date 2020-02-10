package com.abocha.epamtelescope.repository

import io.reactivex.Completable
import io.reactivex.Single

interface ChangePasswordRepository {

    fun requestCode(): Completable

    fun confirmCode(code: String): Single<String>

    fun changePassword(
        changePasswordToken: String,
        oldPassword: String,
        newPassword: String,
        confirmPassword: String
    ): Completable

}
