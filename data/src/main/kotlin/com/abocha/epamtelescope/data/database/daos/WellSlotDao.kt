package com.abocha.epamtelescope.data.database.daos

import androidx.room.Dao
import androidx.room.Query
import com.abocha.epamtelescope.data.database.entities.WellSlotEntity
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by Oleg Sheliakin on 2020-01-14.
 * Contact me by email - olegsheliakin@gmail.com
 */
@Dao
abstract class WellSlotDao : CrudDao<WellSlotEntity>(tableName = WellSlotEntity.TABLE_NAME) {

    @Query("SELECT * FROM ${WellSlotEntity.TABLE_NAME} WHERE platformId = :platformId")
    abstract fun streamById(platformId: Long): Flowable<List<WellSlotEntity>>

    @Query("SELECT * FROM ${WellSlotEntity.TABLE_NAME} WHERE id = :wellSlotId")
    abstract fun getByWellSlotId(wellSlotId: Long): Single<WellSlotEntity>
}
