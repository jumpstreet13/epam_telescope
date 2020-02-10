package com.abocha.epamtelescope.repository

import com.abocha.epamtelescope.entities.SurveyType
import javax.inject.Inject

/**
 * Created by Oleg Sheliakin on 2020-01-29.
 * Contact me by email - olegsheliakin@gmail.com
 */
class SurveyTasksRepositoriesFactory @Inject constructor(
    private val map: Map<SurveyType, @JvmSuppressWildcards SurveyTasksRepository>
) {

    fun create(surveyType: SurveyType): SurveyTasksRepository =
        map[surveyType] ?: throw IllegalStateException("Cannot find repo for $surveyType")
}
