package com.abocha.epamtelescope.data.database.daos

import androidx.room.Dao
import androidx.room.Query
import com.abocha.epamtelescope.data.database.entities.PlatformPhotosSurveyEntity
import com.abocha.epamtelescope.data.database.entities.SurveyShortEntity
import io.reactivex.Flowable

/**
 * Created by Oleg Sheliakin on 2020-01-14.
 * Contact me by email - olegsheliakin@gmail.com
 */
@Dao
abstract class PlatfromPhotosDao :
    CrudDao<PlatformPhotosSurveyEntity>(tableName = PlatformPhotosSurveyEntity.TABLE_NAME) {

    @Query("SELECT * FROM ${PlatformPhotosSurveyEntity.TABLE_NAME} WHERE platformId = :platformId")
    abstract fun streamAll(platformId: Long): Flowable<List<SurveyShortEntity>>
}
