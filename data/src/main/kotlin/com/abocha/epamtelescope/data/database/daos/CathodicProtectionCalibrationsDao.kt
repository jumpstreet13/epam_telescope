package com.abocha.epamtelescope.data.database.daos

import androidx.room.Dao
import androidx.room.Query
import com.abocha.epamtelescope.data.database.entities.CathodicProtectionCalibrationSurveyEntity
import com.abocha.epamtelescope.data.database.entities.SurveyShortEntity
import io.reactivex.Flowable
import io.reactivex.Single
import com.abocha.epamtelescope.data.database.entities.CathodicProtectionCalibrationSurveyEntity.Companion as Entity

/**
 * Created by Oleg Sheliakin on 2020-01-14.
 * Contact me by email - olegsheliakin@gmail.com
 */
@Dao
abstract class CathodicProtectionCalibrationsDao :
    CrudDao<CathodicProtectionCalibrationSurveyEntity>(tableName = Entity.TABLE_NAME) {

    @Query("SELECT * FROM ${Entity.TABLE_NAME} WHERE platformId = :platformId")
    abstract fun streamAll(platformId: Long): Flowable<List<SurveyShortEntity>>

    @Query("SELECT * FROM ${Entity.TABLE_NAME} WHERE id = :id")
    abstract fun getById(id: Long): Single<CathodicProtectionCalibrationSurveyEntity>
}
