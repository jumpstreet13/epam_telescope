package com.abocha.epamtelescope.usecases

import com.abocha.epamtelescope.entities.*
import com.abocha.epamtelescope.repository.SurveyRepository
import com.abocha.epamtelescope.repository.SurveyTasksRepositoriesFactory
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by Oleg Sheliakin on 2020-01-28.
 * Contact me by email - olegsheliakin@gmail.com
 */
class GetSurveysUseCase @Inject constructor(
    private val surveyRepository: SurveyRepository,
    private val surveyTasksRepoFactory: SurveyTasksRepositoriesFactory
) {

    fun execute(platformId: Long): Flowable<List<Survey>> =
        surveyRepository.getSurveys()
            .flatMapPublisher { list ->
                streamAllSurveyTasks(list, platformId)
            }

    @Suppress("UNCHECKED_CAST")
    private fun streamAllSurveyTasks(
        list: List<Survey>,
        platformId: Long
    ): Flowable<List<Survey>> =
        Flowable.combineLatest(list.map { survey ->
            surveyTasksRepoFactory.create(survey.surveyType).streamSurveyTasks(platformId)
                .map { Pair(survey.surveyType, it) }
        }) { arrayOfAnyObjects ->
            val surveyTaskList: List<Pair<SurveyType, List<SurveyTask>>> =
                arrayOfAnyObjects.toList() as List<Pair<SurveyType, List<SurveyTask>>>
            countAllSurveysProgress(list, surveyTaskList)
        }

    private fun countAllSurveysProgress(
        list: List<Survey>,
        surveyTaskList: List<Pair<SurveyType, List<SurveyTask>>>
    ): List<Survey> =
        list.map { survey ->
            survey.copy(
                totalSurveyNumber = surveyTaskList.find { it.first == survey.surveyType }?.second?.size
                    ?: 0,
                syncSurveysNumber = surveyTaskList.find { it.first == survey.surveyType }?.second?.filter {
                    it.surveyType == survey.surveyType &&
                            it.syncState == SyncState.SYNCRONIZED
                }?.size ?: 0
            )
        }


}
