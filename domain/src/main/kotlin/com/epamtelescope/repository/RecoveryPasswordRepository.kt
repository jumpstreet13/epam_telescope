package com.abocha.epamtelescope.repository

import io.reactivex.Completable
import io.reactivex.Single

interface RecoveryPasswordRepository {

    fun requestCode(email: String): Completable

    fun confirmCode(email: String, code: String): Single<String>

    fun changePassword(
        recoveryToken: String,
        newPassword: String,
        confirmPassword: String
    ): Completable

}
