package com.abocha.epamtelescope.data.database.di

import android.content.Context
import androidx.room.Room
import com.abocha.epamtelescope.data.database.AppDatabase
import com.abocha.epamtelescope.data.database.daos.SongDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Oleg Sheliakin on 2020-01-14.
 * Contact me by email - olegsheliakin@gmail.com
 */
@Suppress("TooManyFunctions")
@Module
object DBModule {

    @Singleton
    @Provides
    fun provideDb(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideSongDao(db: AppDatabase): SongDao = db.getSongDao()
}
