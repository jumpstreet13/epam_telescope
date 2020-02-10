package com.abocha.epamtelescope.repository

import com.abocha.epamtelescope.entities.CPCalibration
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by Oleg Sheliakin on 2020-01-24.
 * Contact me by email - olegsheliakin@gmail.com
 */
interface CPCalibrationRepository : SurveyTasksRepository {
    fun save(
        cathodicProtectionCalibration: CPCalibration,
        isDraft: Boolean
    ): Completable

    fun getById(surveyId: Long): Single<CPCalibration>
}
