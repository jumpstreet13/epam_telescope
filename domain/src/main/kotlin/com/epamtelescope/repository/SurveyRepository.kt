package com.abocha.epamtelescope.repository

import com.abocha.epamtelescope.entities.Survey
import io.reactivex.Single

/**
 * Created by Oleg Sheliakin on 2020-01-28.
 * Contact me by email - olegsheliakin@gmail.com
 */
interface SurveyRepository {
    fun getSurveys(): Single<List<Survey>>
}
