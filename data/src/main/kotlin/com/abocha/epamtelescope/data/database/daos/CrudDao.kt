package com.abocha.epamtelescope.data.database.daos

import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery

/**
 * Created by Oleg Sheliakin on 2020-01-14.
 * Contact me by email - olegsheliakin@gmail.com
 */
@Suppress("TooManyFunctions")
@Dao
abstract class CrudDao<ENTITY>(private val tableName: String) {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertOrReplace(entity: ENTITY): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertOrReplace(entities: List<ENTITY>): List<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    protected abstract fun insert(entity: ENTITY): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    protected abstract fun insert(entities: List<ENTITY>): List<Long>

    @Update
    abstract fun update(entity: ENTITY)

    @Update
    abstract fun update(entities: List<ENTITY>)

    @Transaction
    open fun upsert(entity: ENTITY) {
        val id = insert(entity)
        if (id == -1L) {
            update(entity)
        }
    }

    @Transaction
    open fun replaceAll(entities: List<ENTITY>) {
        deleteAll()
        insert(entities)
    }

    @Transaction
    open fun upsert(entities: List<ENTITY>) {
        val insertResult = insert(entities)

        val updateList = entities.filterIndexed { i, _ ->
            insertResult[i] == -1L
        }

        if (!updateList.isEmpty()) {
            update(updateList)
        }
    }

    @Delete
    abstract fun delete(entity: ENTITY)

    @Delete
    abstract fun delete(entities: List<ENTITY>)

    fun deleteAll(): Int {
        val query = SimpleSQLiteQuery("delete from $tableName")
        return doDelete(query)
    }

    fun findAll(): List<ENTITY> {
        val query = SimpleSQLiteQuery("SELECT * from $tableName")
        return doGetAll(query)
    }

    @RawQuery
    protected abstract fun doDelete(query: SupportSQLiteQuery): Int

    @RawQuery
    protected abstract fun doGetAll(query: SupportSQLiteQuery): List<ENTITY>

}
