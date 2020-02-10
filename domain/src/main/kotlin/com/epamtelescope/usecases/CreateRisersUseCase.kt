package com.abocha.epamtelescope.usecases

import com.abocha.epamtelescope.entities.Dictionary
import com.abocha.epamtelescope.entities.Riser
import com.abocha.epamtelescope.repository.ProfileRepository
import com.abocha.epamtelescope.repository.RiserRepository
import com.abocha.epamtelescope.usecases.changepassword.SurveyParams
import io.reactivex.Completable
import javax.inject.Inject

/**
 * Created by Oleg Sheliakin on 2020-01-24.
 * Contact me by email - olegsheliakin@gmail.com
 */
class CreateRisersUseCase @Inject constructor(
    private val risersRepository: RiserRepository,
    private val profileRepository: ProfileRepository
) {

    fun execute(surveyParams: SurveyParams, params: Params): Completable =
        profileRepository
            .getProfile()
            .flatMapCompletable {
                risersRepository.save(
                    riser = createRiser(surveyParams, params, it.id),
                    isDraft = surveyParams.isDraft
                )
            }

    private fun createRiser(surveyParams: SurveyParams, params: Params, inspectorId: Long) =
        Riser(
            id = surveyParams.id,
            date = surveyParams.date,
            campaignId = surveyParams.campaignId,
            platformId = surveyParams.platformId,
            inspectorId = inspectorId,
            platfromArea = params.platfromArea,
            riserType = params.riserType,
            segmentNumber = params.segmentNumber,
            apirp14CName = params.apirp14CName,
            drawingId = params.drawingId,
            diamiter = params.diamiter,
            mechDamage = params.mechDamage,
            mechDamageComment = params.mechDamageComment,
            coatingCondition = params.coatingCondition,
            coatingConditionComment = params.coatingConditionComment,
            readingAboveFlange = params.readingAboveFlange,
            readingBellowFlange = params.readingBellowFlange,
            repairsComment = params.repairsComment,
            hseComment = params.hseComment,
            isoFlangeAccess = params.isoFlangeAccess,
            paintedOnTheRiser = params.paintedOnTheRiser,
            comments = params.comments,
            riserGuardPresent = params.riserGuardPresent,
            flangeIsoKitInstalled = params.flangeIsoKitInstalled,
            flangeIsoKitWorking = params.flangeIsoKitWorking,
            hseHazard = params.hseHazard
        )

    data class Params(
        val platfromArea: Dictionary? = null,
        val riserType: Dictionary? = null,
        val segmentNumber: Int? = null,
        val apirp14CName: String,
        val drawingId: Int? = null,
        val diamiter: Int? = null,
        val mechDamage: Dictionary? = null,
        val mechDamageComment: String,
        val coatingCondition: Dictionary? = null,
        val coatingConditionComment: String,
        val readingAboveFlange: String,
        val readingBellowFlange: String,
        val repairsComment: String,
        val hseComment: String,
        val isoFlangeAccess: String,
        val paintedOnTheRiser: String,
        val comments: String,
        val riserGuardPresent: Boolean = false,
        val flangeIsoKitInstalled: Boolean = false,
        val flangeIsoKitWorking: Boolean = false,
        val hseHazard: Boolean = false
    )

}
