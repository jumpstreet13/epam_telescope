package com.abocha.epamtelescope.usecases

import com.abocha.epamtelescope.entities.Component
import com.abocha.epamtelescope.entities.Dictionary
import com.abocha.epamtelescope.repository.ComponentRepository
import com.abocha.epamtelescope.repository.ProfileRepository
import com.abocha.epamtelescope.usecases.changepassword.SurveyParams
import io.reactivex.Completable
import javax.inject.Inject

/**
 * Created by Oleg Sheliakin on 2020-01-24.
 * Contact me by email - olegsheliakin@gmail.com
 */
class CreateComponentUseCase @Inject constructor(
    private val componentRepository: ComponentRepository,
    private val profileRepository: ProfileRepository
) {

    fun execute(
        surveyParams: SurveyParams,
        params: Params
    ): Completable =
        profileRepository
            .getProfile()
            .flatMapCompletable {
                val survey = Component(
                    id = surveyParams.id,
                    date = surveyParams.date,
                    campaignId = surveyParams.campaignId,
                    platformId = surveyParams.platformId,
                    inspectorId = it.id,
                    mechanicalDamage = params.mechanicalDamage,
                    mechanicalDamageComment = params.mechanicalDamageComment,
                    coatingCondition = params.coatingCondition,
                    coatingConditionComment = params.coatingConditionComment,
                    hseHazard = params.hseHazard,
                    componentType = params.componentType,
                    hseComment = params.hseComment,
                    comment = params.comment,
                    platformArea = params.platformArea
                )
                componentRepository.save(survey, surveyParams.isDraft)
            }

    data class Params(
        val platformArea: Dictionary?,
        val componentType: Dictionary?,
        val mechanicalDamage: Dictionary?,
        val coatingCondition: Dictionary?,
        val mechanicalDamageComment: String,
        val coatingConditionComment: String,
        val hseComment: String,
        val comment: String,
        val hseHazard: Boolean
    )
}
