package com.abocha.epamtelescope.usecases

import com.abocha.epamtelescope.entities.ClampData
import com.abocha.epamtelescope.entities.RiserClamp
import com.abocha.epamtelescope.repository.ProfileRepository
import com.abocha.epamtelescope.repository.RiserClampRepository
import io.reactivex.Completable
import org.threeten.bp.ZonedDateTime
import javax.inject.Inject

/**
 * Created by Oleg Sheliakin on 2020-01-24.
 * Contact me by email - olegsheliakin@gmail.com
 */
class CreateRiserClampsUseCase @Inject constructor(
    private val riserClampRepository: RiserClampRepository,
    private val profileRepository: ProfileRepository
) {

    fun execute(params: Params): Completable =
        profileRepository
            .getProfile()
            .flatMapCompletable {
                val survey = RiserClamp(
                    id = params.id,
                    date = params.date,
                    drawingReference = params.drawingReference,
                    clamps = params.clamps,
                    campaignId = params.campaignId,
                    platformId = params.platformId,
                    inspectorId = it.id
                )
                riserClampRepository.save(survey, params.isDraft)
            }

    data class Params(
        val id: Long,
        val drawingReference: Int?,
        val isDraft: Boolean,
        val clamps: List<ClampData>,
        val date: ZonedDateTime,
        val platformId: Long,
        val campaignId: Long
    )
}
