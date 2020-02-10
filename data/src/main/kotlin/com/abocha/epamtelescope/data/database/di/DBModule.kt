package com.abocha.epamtelescope.data.database.di

import android.content.Context
import androidx.room.Room
import com.abocha.epamtelescope.data.database.GirDatabase
import com.abocha.epamtelescope.data.database.daos.*
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
    fun provideDb(context: Context): GirDatabase =
        Room.databaseBuilder(context, GirDatabase::class.java, GirDatabase.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideAssignmentsDao(db: GirDatabase): AssignmentDao = db.getAssignmentDao()

    @Singleton
    @Provides
    fun providePlatformsDao(db: GirDatabase): PlatformsDao = db.getPlatformsDao()

    @Singleton
    @Provides
    fun provideDictionaryDao(db: GirDatabase): DictionaryDao = db.getDictionaryDao()

    @Singleton
    @Provides
    fun provideRisesClampDao(db: GirDatabase): RiserClampsDao = db.getRiserClampDao()

    @Singleton
    @Provides
    fun provideComponentsDao(db: GirDatabase): ComponentsDao = db.getComponentsDao()

    @Singleton
    @Provides
    fun provideRisersDao(db: GirDatabase): RisersDao = db.getRisersDao()

    @Singleton
    @Provides
    fun provideConductorsDao(db: GirDatabase): ConductorsDao = db.getConductorsDao()

    @Singleton
    @Provides
    fun provideCathodicProtectionsDao(db: GirDatabase):
            CathodicProtectionsDao = db.getCathodicProtectionsDao()

    @Singleton
    @Provides
    fun provideCathodicProtectionCalibrationsDao(db: GirDatabase):
            CathodicProtectionCalibrationsDao = db.getCathodicProtectionCalibrationsDao()

    @Singleton
    @Provides
    fun provideIsimsDao(db: GirDatabase): IsimsDao = db.getIsimsDao()

    @Singleton
    @Provides
    fun providePlatfromPhotosDao(db: GirDatabase): PlatfromPhotosDao = db.getPlatfromPhotosDao()

    @Singleton
    @Provides
    fun provideWellSlotDao(db: GirDatabase): WellSlotDao = db.getWellSlotsDAo()
}
