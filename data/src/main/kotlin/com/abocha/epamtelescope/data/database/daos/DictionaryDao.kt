package com.abocha.epamtelescope.data.database.daos

import androidx.room.Dao
import androidx.room.Query
import com.abocha.epamtelescope.data.database.entities.DictionariesEntity
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
abstract class DictionaryDao :
    CrudDao<DictionariesEntity>(tableName = DictionariesEntity.TABLE_NAME) {

    @Query("SELECT * FROM ${DictionariesEntity.TABLE_NAME}")
    abstract fun streamDictionary(): Flowable<List<DictionariesEntity>>

    @Query("SELECT * FROM ${DictionariesEntity.TABLE_NAME}")
    abstract fun getDictionary(): Single<List<DictionariesEntity>>

    @Query("SELECT * FROM ${DictionariesEntity.TABLE_NAME}")
    abstract fun getAllConductorsNotReactive(): List<DictionariesEntity>
}
