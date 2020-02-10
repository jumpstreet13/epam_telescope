package com.abocha.epamtelescope.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.abocha.epamtelescope.data.database.converter.*
import com.abocha.epamtelescope.data.database.daos.*
import com.abocha.epamtelescope.data.database.entities.*

/**
 * Created by Oleg Sheliakin on 2020-01-14.
 * Contact me by email - olegsheliakin@gmail.com
 */
@Database(
    entities = [
        AssignmentEntity::class,
        PlatformEntity::class,
        DictionariesEntity::class,
        RiserClampSurveyEntity::class,
        RiserSurveyEntity::class,
        ComponentSurveyEntity::class,
        IsimsSurveyEntity::class,
        CathodicProtectionSurveyEntity::class,
        CathodicProtectionCalibrationSurveyEntity::class,
        PlatformPhotosSurveyEntity::class,
        ConductorSurveyEntity::class,
        WellSlotEntity::class
    ],
    version = 20
)

@TypeConverters(
    value = [
        DictionariesConverter::class,
        DictionaryConverter::class,
        ListClampDataConverter::class,
        SyncStateConverter::class,
        ZonedDateConverter::class,
        PhotosConverter::class
    ]
)
@Suppress("TooManyFunctions")
abstract class GirDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "gir_db"
    }

    abstract fun getAssignmentDao(): AssignmentDao
    abstract fun getPlatformsDao(): PlatformsDao
    abstract fun getDictionaryDao(): DictionaryDao
    abstract fun getRiserClampDao(): RiserClampsDao
    abstract fun getRisersDao(): RisersDao
    abstract fun getComponentsDao(): ComponentsDao
    abstract fun getConductorsDao(): ConductorsDao
    abstract fun getCathodicProtectionCalibrationsDao(): CathodicProtectionCalibrationsDao
    abstract fun getCathodicProtectionsDao(): CathodicProtectionsDao
    abstract fun getIsimsDao(): IsimsDao
    abstract fun getPlatfromPhotosDao(): PlatfromPhotosDao
    abstract fun getWellSlotsDAo(): WellSlotDao

}
