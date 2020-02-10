package com.abocha.epamtelescope.usecases

import com.abocha.epamtelescope.entities.CathodicProtection
import com.abocha.epamtelescope.entities.Photo
import com.abocha.epamtelescope.repository.CathodicProtectionRepository
import com.abocha.epamtelescope.repository.ProfileRepository
import com.abocha.epamtelescope.usecases.changepassword.SurveyParams
import io.reactivex.Completable
import javax.inject.Inject

/**
 * Created by Oleg Sheliakin on 2020-01-24.
 * Contact me by email - olegsheliakin@gmail.com
 */
class CreateCathodicProtectionUseCase @Inject constructor(
    private val cathodicProtectionRepository: CathodicProtectionRepository,
    private val profileRepository: ProfileRepository
) {

    fun execute(surveyParams: SurveyParams, params: Params): Completable =
        profileRepository
            .getProfile()
            .flatMapCompletable {
                val survey = CathodicProtection(
                    id = surveyParams.id,
                    date = surveyParams.date,
                    campaignId = surveyParams.campaignId,
                    platformId = surveyParams.platformId,
                    inspectorId = it.id,
                    comment = params.comment,
                    legId = params.legId,
                    calibrationNumber = params.calibrationNumber,
                    depthTenReading = params.depthTenReading,
                    depthTwentyReading = params.depthTwentyReading,
                    depthThirtyReading = params.depthThirtyReading,
                    depthFortyReading = params.depthFortyReading,
                    photos = params.photos
                )
                cathodicProtectionRepository.save(survey, surveyParams.isDraft)
            }

    data class Params(
        val legId: String,
        val comment: String,
        val calibrationNumber: Int?,
        val depthTenReading: Int?,
        val depthTwentyReading: Int?,
        val depthThirtyReading: Int?,
        val depthFortyReading: Int?,
        val photos: List<Photo>
    )
}
