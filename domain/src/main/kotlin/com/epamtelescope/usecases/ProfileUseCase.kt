package com.abocha.epamtelescope.usecases

import com.abocha.epamtelescope.repository.ProfileRepository
import javax.inject.Inject

class ProfileUseCase @Inject constructor(private val profileRepository: ProfileRepository) {

    fun requestProfile() = profileRepository.requestProfile()

    fun getProfile() = profileRepository.getProfile()
}
