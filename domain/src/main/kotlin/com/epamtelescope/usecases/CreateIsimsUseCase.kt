package com.abocha.epamtelescope.usecases

import com.abocha.epamtelescope.entities.Isims
import com.abocha.epamtelescope.repository.IsimsRepository
import com.abocha.epamtelescope.repository.ProfileRepository
import com.abocha.epamtelescope.usecases.changepassword.SurveyParams
import io.reactivex.Completable
import javax.inject.Inject

/**
 * Created by Oleg Sheliakin on 2020-01-24.
 * Contact me by email - olegsheliakin@gmail.com
 */
class CreateIsimsUseCase @Inject constructor(
    private val isimsRepository: IsimsRepository,
    private val profileRepository: ProfileRepository
) {

    fun execute(surveyParams: SurveyParams, params: Params): Completable =
        profileRepository
            .getProfile()
            .flatMapCompletable {
                isimsRepository.save(
                    isims = createSurvey(surveyParams, params, it.id),
                    isDraft = surveyParams.isDraft
                )
            }

    private fun createSurvey(surveyParams: SurveyParams, params: Params, inspectorId: Long) =
        Isims(
            id = surveyParams.id,
            date = surveyParams.date,
            campaignId = surveyParams.campaignId,
            platformId = surveyParams.platformId,
            inspectorId = inspectorId,
            anomalyDescription = params.anomalyDescription,
            anomalyNumber = params.anomalyNumber
        )

    data class Params(
        var anomalyNumber: Int?,
        var anomalyDescription: String
    )

}
