package com.abocha.epamtelescope.repository

import io.reactivex.Completable

/**
 * Created by Oleg Sheliakin on 2019-12-23.
 * Contact me by email - olegsheliakin@gmail.com
 */
interface AuthRepository {
    fun login(email: String, password: String): Completable

    fun logout(): Completable

    fun isLoggedIn(): Boolean
}
