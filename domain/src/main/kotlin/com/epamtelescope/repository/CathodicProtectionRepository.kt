package com.abocha.epamtelescope.repository

import com.abocha.epamtelescope.entities.CathodicProtection
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by Oleg Sheliakin on 2020-01-24.
 * Contact me by email - olegsheliakin@gmail.com
 */
interface CathodicProtectionRepository : SurveyTasksRepository {
    fun save(cathodicProtection: CathodicProtection, isDraft: Boolean): Completable
    fun getById(surveyId: Long): Single<CathodicProtection>
}
