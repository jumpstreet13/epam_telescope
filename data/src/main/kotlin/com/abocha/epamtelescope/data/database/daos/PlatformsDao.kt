package com.abocha.epamtelescope.data.database.daos

import androidx.room.Dao
import androidx.room.Query
import com.abocha.epamtelescope.data.database.entities.PlatformEntity
import io.reactivex.Flowable

/**
 * Created by Oleg Sheliakin on 2020-01-14.
 * Contact me by email - olegsheliakin@gmail.com
 */
@Dao
abstract class PlatformsDao : CrudDao<PlatformEntity>(tableName = PlatformEntity.TABLE_NAME) {

    @Query("SELECT * FROM ${PlatformEntity.TABLE_NAME} WHERE assignmentId = :assignmentId")
    abstract fun streamAllPlatforms(assignmentId: Long): Flowable<List<PlatformEntity>>
}
