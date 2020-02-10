package com.abocha.epamtelescope.usecases

import com.abocha.epamtelescope.entities.ConductorWellHead
import com.abocha.epamtelescope.entities.Dictionary
import com.abocha.epamtelescope.entities.WellSlot
import com.abocha.epamtelescope.repository.ConductorRepository
import com.abocha.epamtelescope.repository.ProfileRepository
import com.abocha.epamtelescope.usecases.changepassword.SurveyParams
import io.reactivex.Completable
import org.threeten.bp.LocalDate
import javax.inject.Inject

/**
 * Created by Oleg Sheliakin on 2020-01-24.
 * Contact me by email - olegsheliakin@gmail.com
 */
class CreateConductorWellheadUseCase @Inject constructor(
    private val conductorRepository: ConductorRepository,
    private val profileRepository: ProfileRepository
) {

    fun execute(surveyParams: SurveyParams, params: Params): Completable =
        profileRepository.getProfile()
            .flatMapCompletable {
                val survey = ConductorWellHead(
                    id = surveyParams.id,
                    date = surveyParams.date,
                    campaignId = surveyParams.campaignId,
                    platformId = surveyParams.platformId,
                    inspectorId = it.id,
                    coatingCondition = params.coatingCondition,
                    hseComment = params.hseComment,
                    coatingConditionComment = params.coatingConditionComment,
                    hseHazard = params.hseHazard,
                    conductorArea = params.conductorArea,
                    diameter = params.diameter,
                    comment = params.comment,
                    gap = params.gap,
                    guideDamage = params.guideDamage,
                    mechanicalDamage = params.mechanicalDamage,
                    mechanicalDamageComment = params.mechanicalDamageComment,
                    shims = params.shims,
                    wellSlot = params.wellSlot
                )
                conductorRepository.save(survey, surveyParams.isDraft)
            }

    data class Params(
        val date: LocalDate,
        val platformId: Long?,
        val mechanicalDamage: Dictionary?,
        val mechanicalDamageComment: String,
        val coatingCondition: Dictionary?,
        val coatingConditionComment: String,
        val conductorArea: Dictionary?,
        val hseComment: String,
        val comment: String,
        val hseHazard: Boolean,
        val wellSlot: WellSlot?,
        val diameter: Int?,
        val gap: Int?,
        val shims: Boolean,
        val guideDamage: Boolean
    )
}
