package com.abocha.epamtelescope.usecases

import com.abocha.epamtelescope.entities.CPCalibration
import com.abocha.epamtelescope.repository.CPCalibrationRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Oleg Sheliakin on 2020-01-24.
 * Contact me by email - olegsheliakin@gmail.com
 */
class GetCPCalibrationUseCase @Inject constructor(
    private val cpCalibrationRepository: CPCalibrationRepository
) {

    fun execute(surveyId: Long): Single<CPCalibration> =
        cpCalibrationRepository.getById(surveyId)

}
