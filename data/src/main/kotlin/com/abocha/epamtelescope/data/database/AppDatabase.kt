package com.abocha.epamtelescope.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

import com.abocha.epamtelescope.data.database.daos.SongDao
import com.abocha.epamtelescope.data.database.entities.SongEntity

/**
 * Created by Oleg Sheliakin on 2020-01-14.
 * Contact me by email - olegsheliakin@gmail.com
 */
@Database(
    entities = [
        SongEntity::class
    ],
    version = 20
)

@Suppress("TooManyFunctions")
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "abocha_player_database"
    }

    abstract fun getSongDao(): SongDao
}
