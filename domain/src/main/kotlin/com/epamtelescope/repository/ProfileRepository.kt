package com.abocha.epamtelescope.repository

import com.abocha.epamtelescope.entities.User
import io.reactivex.Completable
import io.reactivex.Single

interface ProfileRepository {
    fun requestProfile(): Completable
    fun getProfile(): Single<User>
}
