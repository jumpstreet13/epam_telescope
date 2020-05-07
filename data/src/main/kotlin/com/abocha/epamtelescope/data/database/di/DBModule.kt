package com.abocha.epamtelescope.data.database.di

import android.content.Context
import androidx.room.Room
import com.abocha.epamtelescope.data.database.AppDatabase
import com.abocha.epamtelescope.data.database.daos.AssignmentDao
import com.abocha.epamtelescope.data.database.daos.CathodicProtectionCalibrationsDao
import com.abocha.epamtelescope.data.database.daos.CathodicProtectionsDao
import com.abocha.epamtelescope.data.database.daos.ComponentsDao
import com.abocha.epamtelescope.data.database.daos.ConductorsDao
import com.abocha.epamtelescope.data.database.daos.DictionaryDao
import com.abocha.epamtelescope.data.database.daos.IsimsDao
import com.abocha.epamtelescope.data.database.daos.PlatformsDao
import com.abocha.epamtelescope.data.database.daos.PlatfromPhotosDao
import com.abocha.epamtelescope.data.database.daos.RiserClampsDao
import com.abocha.epamtelescope.data.database.daos.RisersDao
import com.abocha.epamtelescope.data.database.daos.SongDao
import com.abocha.epamtelescope.data.database.daos.WellSlotDao
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
    fun provideAssignmentsDao(db: AppDatabase): AssignmentDao = db.getAssignmentDao()

    @Singleton
    @Provides
    fun provideSongDao(db: AppDatabase): SongDao = db.getSongDao()

    @Singleton
    @Provides
    fun providePlatformsDao(db: AppDatabase): PlatformsDao = db.getPlatformsDao()

    @Singleton
    @Provides
    fun provideDictionaryDao(db: AppDatabase): DictionaryDao = db.getDictionaryDao()

    @Singleton
    @Provides
    fun provideRisesClampDao(db: AppDatabase): RiserClampsDao = db.getRiserClampDao()

    @Singleton
    @Provides
    fun provideComponentsDao(db: AppDatabase): ComponentsDao = db.getComponentsDao()

    @Singleton
    @Provides
    fun provideRisersDao(db: AppDatabase): RisersDao = db.getRisersDao()

    @Singleton
    @Provides
    fun provideConductorsDao(db: AppDatabase): ConductorsDao = db.getConductorsDao()

    @Singleton
    @Provides
    fun provideCathodicProtectionsDao(db: AppDatabase):
            CathodicProtectionsDao = db.getCathodicProtectionsDao()

    @Singleton
    @Provides
    fun provideCathodicProtectionCalibrationsDao(db: AppDatabase):
            CathodicProtectionCalibrationsDao = db.getCathodicProtectionCalibrationsDao()

    @Singleton
    @Provides
    fun provideIsimsDao(db: AppDatabase): IsimsDao = db.getIsimsDao()

    @Singleton
    @Provides
    fun providePlatfromPhotosDao(db: AppDatabase): PlatfromPhotosDao = db.getPlatfromPhotosDao()

    @Singleton
    @Provides
    fun provideWellSlotDao(db: AppDatabase): WellSlotDao = db.getWellSlotsDAo()
}
