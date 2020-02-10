package com.abocha.epamtelescope.usecases

import com.abocha.epamtelescope.entities.SurveyTask
import com.abocha.epamtelescope.entities.SurveyType
import com.abocha.epamtelescope.repository.SurveyTasksRepositoriesFactory
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by Oleg Sheliakin on 2020-01-28.
 * Contact me by email - olegsheliakin@gmail.com
 */
class GetSurveyTasksUseCase @Inject constructor(
    private val surveyTasksRepoFactory: SurveyTasksRepositoriesFactory
) {

    fun execute(platformId: Long, surveyType: SurveyType): Flowable<List<SurveyTask>> =
        Flowable.fromCallable {
            surveyTasksRepoFactory.create(surveyType)
        }.flatMap {
            it.streamSurveyTasks(platformId)
        }
}
