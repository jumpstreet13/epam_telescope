package com.abocha.epamtelescope.data.repositories

import com.abocha.epamtelescope.data.manager.ProfileManager
import com.abocha.epamtelescope.data.network.services.ProfileService
import com.abocha.epamtelescope.entities.User
import com.abocha.epamtelescope.repository.ProfileRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileManager: ProfileManager,
    private val profileService: ProfileService
) : ProfileRepository {

    override fun requestProfile(): Completable =
        profileService.requestProfile()
            .doOnSuccess(profileManager::saveProfile)
            .ignoreElement()

    override fun getProfile(): Single<User> =
        profileManager.requestProfile()
}
