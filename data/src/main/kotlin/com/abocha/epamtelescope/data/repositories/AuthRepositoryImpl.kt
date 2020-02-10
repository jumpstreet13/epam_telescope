package com.abocha.epamtelescope.data.repositories

import androidx.work.WorkManager
import com.abocha.epamtelescope.data.manager.ProfileManager
import com.abocha.epamtelescope.data.network.cleaner.DataCleaner
import com.abocha.epamtelescope.data.network.models.requests.LoginRequest
import com.abocha.epamtelescope.data.network.models.requests.RefreshRequest
import com.abocha.epamtelescope.data.network.services.AuthService
import com.abocha.epamtelescope.data.network.services.MainService
import com.abocha.epamtelescope.oauth2token.session.OAuth2Session
import com.abocha.epamtelescope.repository.AuthRepository
import io.reactivex.Completable
import javax.inject.Inject

/**
 * Created by Oleg Sheliakin on 2019-12-23.
 * Contact me by email - olegsheliakin@gmail.com
 */
class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService,
    private val mainService: MainService,
    private val oAuth2Session: OAuth2Session,
    private val profileManager: ProfileManager,
    private val dataCleaner: DataCleaner,
    private val workManager: WorkManager
) : AuthRepository {

    override fun login(email: String, password: String): Completable =
        authService
            .login(LoginRequest(email, password))
            .doOnSuccess {
                oAuth2Session.accessToken = it.accessToken
                oAuth2Session.refreshToken = it.refreshToken
            }
            .doOnSuccess {
                profileManager.saveProfile(it.userProfileResponse)
            }
            .ignoreElement()

    override fun logout(): Completable =
        mainService.logout(RefreshRequest(oAuth2Session.refreshToken ?: ""))
            .onErrorComplete()
            .andThen(dataCleaner.clean())
            .andThen(Completable.fromAction {
                workManager.cancelAllWork()
            })

    override fun isLoggedIn(): Boolean =
        oAuth2Session.accessToken != null && oAuth2Session.refreshToken != null

}
