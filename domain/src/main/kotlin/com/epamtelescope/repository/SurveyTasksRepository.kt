package com.abocha.epamtelescope.repository

import com.abocha.epamtelescope.entities.SurveyTask
import io.reactivex.Flowable

/**
 * Created by Oleg Sheliakin on 2020-01-29.
 * Contact me by email - olegsheliakin@gmail.com
 */
interface SurveyTasksRepository {
    fun streamSurveyTasks(platformId: Long): Flowable<List<SurveyTask>>
}
