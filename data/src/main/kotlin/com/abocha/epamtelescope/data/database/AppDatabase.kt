package com.abocha.epamtelescope.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.abocha.epamtelescope.data.database.converter.DictionariesConverter
import com.abocha.epamtelescope.data.database.converter.DictionaryConverter
import com.abocha.epamtelescope.data.database.converter.ListClampDataConverter
import com.abocha.epamtelescope.data.database.converter.PhotosConverter
import com.abocha.epamtelescope.data.database.converter.SyncStateConverter
import com.abocha.epamtelescope.data.database.converter.ZonedDateConverter
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
import com.abocha.epamtelescope.data.database.entities.AssignmentEntity
import com.abocha.epamtelescope.data.database.entities.CathodicProtectionCalibrationSurveyEntity
import com.abocha.epamtelescope.data.database.entities.CathodicProtectionSurveyEntity
import com.abocha.epamtelescope.data.database.entities.ComponentSurveyEntity
import com.abocha.epamtelescope.data.database.entities.ConductorSurveyEntity
import com.abocha.epamtelescope.data.database.entities.DictionariesEntity
import com.abocha.epamtelescope.data.database.entities.IsimsSurveyEntity
import com.abocha.epamtelescope.data.database.entities.PlatformEntity
import com.abocha.epamtelescope.data.database.entities.PlatformPhotosSurveyEntity
import com.abocha.epamtelescope.data.database.entities.RiserClampSurveyEntity
import com.abocha.epamtelescope.data.database.entities.RiserSurveyEntity
import com.abocha.epamtelescope.data.database.entities.SongEntity
import com.abocha.epamtelescope.data.database.entities.WellSlotEntity

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
        WellSlotEntity::class,
        SongEntity::class
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
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "abocha_player_database"
    }

    abstract fun getSongDao(): SongDao
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
