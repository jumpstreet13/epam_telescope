package com.abocha.epamtelescope.data.database.daos

import androidx.room.Dao
import androidx.room.Query
import com.abocha.epamtelescope.data.database.entities.AssignmentEntity
import io.reactivex.Flowable

/**
 * Created by Oleg Sheliakin on 2020-01-14.
 * Contact me by email - olegsheliakin@gmail.com
 */
@Dao
abstract class AssignmentDao : CrudDao<AssignmentEntity>(tableName = AssignmentEntity.TABLE_NAME) {

    @Query("SELECT * FROM ${AssignmentEntity.TABLE_NAME}")
    abstract fun streamAllAssignments(): Flowable<List<AssignmentEntity>>
}
