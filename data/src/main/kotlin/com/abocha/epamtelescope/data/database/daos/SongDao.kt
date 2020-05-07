package com.abocha.epamtelescope.data.database.daos

import androidx.room.Dao
import androidx.room.Query
import com.abocha.epamtelescope.data.database.entities.SongEntity
import io.reactivex.Flowable

/**
 * @author Magomedov Abakar
 */
@Dao
abstract class SongDao : CrudDao<SongEntity>(tableName = SongEntity.TABLE_NAME) {

    @Query("SELECT * FROM ${SongEntity.TABLE_NAME}")
    abstract fun streamAllSongs(): Flowable<List<SongEntity>>
}