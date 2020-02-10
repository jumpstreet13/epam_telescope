package com.abocha.epamtelescope.usecases

import com.abocha.epamtelescope.entities.CPCalibration
import com.abocha.epamtelescope.repository.CPCalibrationRepository
import com.abocha.epamtelescope.repository.ProfileRepository
import com.abocha.epamtelescope.usecases.changepassword.SurveyParams
import io.reactivex.Completable
import javax.inject.Inject

/**
 * Created by Oleg Sheliakin on 2020-01-24.
 * Contact me by email - olegsheliakin@gmail.com
 */
class CreateCPCalibrationUseCase @Inject constructor(
    private val cpCalibrationRepository: CPCalibrationRepository,
    private val profileRepository: ProfileRepository
) {

    fun execute(surveyParams: SurveyParams, params: Params): Completable =
        profileRepository
            .getProfile()
            .flatMapCompletable {
                val survey = CPCalibration(
                    id = surveyParams.id,
                    date = surveyParams.date,
                    campaignId = surveyParams.campaignId,
                    platformId = surveyParams.platformId,
                    inspectorId = it.id,
                    calibrationNumber = params.calibrationNumber,
                    multimeterBrand = params.multimeterBrand,
                    multimeterSN = params.multimeterSN,
                    postCalibration = params.postCalibration,
                    preCalibration = params.preCalibration
                )
                cpCalibrationRepository.save(survey, surveyParams.isDraft)
            }

    data class Params(
        val calibrationNumber: Int?,
        val multimeterBrand: String,
        val multimeterSN: String,
        val preCalibration: Int?,
        val postCalibration: Int?
    )

}
