package com.abocha.epamtelescope.repository

import com.abocha.epamtelescope.entities.WellSlot
import io.reactivex.Observable
import io.reactivex.Single

interface WellSlotsRepository {

    fun getWellSlots(platformId: Long): Observable<List<WellSlot>>

    fun getWellSlot(wellSlotId: Long): Single<WellSlot>
}
