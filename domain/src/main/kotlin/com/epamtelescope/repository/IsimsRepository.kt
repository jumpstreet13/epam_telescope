package com.abocha.epamtelescope.repository

import com.abocha.epamtelescope.entities.Isims
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by Oleg Sheliakin on 2020-01-24.
 * Contact me by email - olegsheliakin@gmail.com
 */
interface IsimsRepository : SurveyTasksRepository {
    fun save(isims: Isims, isDraft: Boolean): Completable
    fun getById(surveyId: Long): Single<Isims>
}