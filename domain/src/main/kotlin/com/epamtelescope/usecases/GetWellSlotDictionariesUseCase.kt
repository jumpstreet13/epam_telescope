package com.abocha.epamtelescope.usecases

import com.abocha.epamtelescope.repository.WellSlotsRepository
import javax.inject.Inject

/**
 * Created by Oleg Sheliakin on 2020-02-04.
 * Contact me by email - olegsheliakin@gmail.com
 */
class GetWellSlotDictionariesUseCase @Inject constructor(
    private val wellSlotsRepository: WellSlotsRepository
) {

    fun execute(platformId: Long) =
        wellSlotsRepository.getWellSlots(platformId)
}
