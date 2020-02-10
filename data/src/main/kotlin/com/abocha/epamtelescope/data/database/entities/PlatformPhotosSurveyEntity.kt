package com.abocha.epamtelescope.data.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.abocha.epamtelescope.entities.EMPTY_ID
import com.abocha.epamtelescope.entities.PlatfromPhotos
import com.abocha.epamtelescope.entities.SyncState

/**
 * Created by Oleg Sheliakin on 2020-01-28.
 * Contact me by email - olegsheliakin@gmail.com
 */

@Entity(tableName = PlatformPhotosSurveyEntity.TABLE_NAME)
data class PlatformPhotosSurveyEntity(
    @PrimaryKey(autoGenerate = true)
    override val id: Long = EMPTY_ID,
    @Embedded
    override val embedded: EmbeddedSurveyEntity
) : SurveyEntity {

    companion object {
        const val TABLE_NAME = "platforms_photos_survey_entity"
    }
}

fun PlatfromPhotos.toDbModel(syncState: SyncState) = PlatformPhotosSurveyEntity(
    id = id,
    embedded = EmbeddedSurveyEntity(
        syncState = syncState,
        date = date,
        inspectorId = inspectorId,
        platformId = platformId,
        campaignId = campaignId
    )
)
