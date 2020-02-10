package com.abocha.epamtelescope.data.repositories

import com.abocha.epamtelescope.data.database.daos.WellSlotDao
import com.abocha.epamtelescope.data.database.entities.toDomainModel
import com.abocha.epamtelescope.data.database.entities.toDomainModels
import com.abocha.epamtelescope.entities.WellSlot
import com.abocha.epamtelescope.repository.WellSlotsRepository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Oleg Sheliakin on 2020-02-04.
 * Contact me by email - olegsheliakin@gmail.com
 */
class WellSlotsRepositoryImpl @Inject constructor(
    private val wellSlotDao: WellSlotDao
) : WellSlotsRepository {

    override fun getWellSlots(platformId: Long): Observable<List<WellSlot>> =
        wellSlotDao.streamById(platformId)
            .map { it.toDomainModels() }
            .toObservable()

    override fun getWellSlot(wellSlotId: Long): Single<WellSlot> =
        wellSlotDao.getByWellSlotId(wellSlotId)
            .map {
                it.toDomainModel()
            }
}
