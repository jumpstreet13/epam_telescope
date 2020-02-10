package com.abocha.epamtelescope.usecases

import com.abocha.epamtelescope.entities.PlatfromPhotos
import com.abocha.epamtelescope.repository.PlatformPhotosRepository
import com.abocha.epamtelescope.repository.ProfileRepository
import com.abocha.epamtelescope.usecases.changepassword.SurveyParams
import io.reactivex.Completable
import javax.inject.Inject

/**
 * Created by Oleg Sheliakin on 2020-01-24.
 * Contact me by email - olegsheliakin@gmail.com
 */
class CreatePlatformPhotosUseCase @Inject constructor(
    private val platformPhotosRepository: PlatformPhotosRepository,
    private val profileRepository: ProfileRepository
) {

    fun execute(params: SurveyParams): Completable =
        profileRepository
            .getProfile()
            .flatMapCompletable {
                val survey = PlatfromPhotos(
                    id = params.id,
                    date = params.date,
                    campaignId = params.campaignId,
                    platformId = params.platformId,
                    inspectorId = it.id
                )
                platformPhotosRepository.save(survey, params.isDraft)
            }
}
