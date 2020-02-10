package com.abocha.epamtelescope.repository

import com.abocha.epamtelescope.entities.RiserClamp
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by Oleg Sheliakin on 2020-01-24.
 * Contact me by email - olegsheliakin@gmail.com
 */
interface RiserClampRepository : SurveyTasksRepository {
    fun save(riserClampSurvey: RiserClamp, isDraft: Boolean): Completable
    fun getById(surveyId: Long): Single<RiserClamp>
}
