package com.abocha.epamtelescope.data.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.abocha.epamtelescope.entities.CathodicProtection
import com.abocha.epamtelescope.entities.EMPTY_ID
import com.abocha.epamtelescope.entities.Photo
import com.abocha.epamtelescope.entities.SyncState

/**
 * Created by Oleg Sheliakin on 2020-01-28.
 * Contact me by email - olegsheliakin@gmail.com
 */

@Entity(tableName = CathodicProtectionSurveyEntity.TABLE_NAME)
data class CathodicProtectionSurveyEntity(
    @PrimaryKey(autoGenerate = true)
    override val id: Long = EMPTY_ID,
    @Embedded
    override val embedded: EmbeddedSurveyEntity,
    val calibrationNumber: Int?,
    val depthTenReading: Int?,
    val depthTwentyReading: Int?,
    val depthThirtyReading: Int?,
    val depthFortyReading: Int?,
    val legId: String,
    val comment: String,
    val photos: List<Photo>
) : SurveyEntity {

    companion object {
        const val TABLE_NAME = "cathodic_protection_survey_entity"
    }
}

fun CathodicProtection.toDbModel(syncState: SyncState) = CathodicProtectionSurveyEntity(
    id = id,
    embedded = EmbeddedSurveyEntity(
        syncState = syncState,
        date = date,
        inspectorId = inspectorId,
        platformId = platformId,
        campaignId = campaignId
    ),
    depthFortyReading = depthFortyReading,
    depthThirtyReading = depthThirtyReading,
    depthTwentyReading = depthTwentyReading,
    depthTenReading = depthTenReading,
    calibrationNumber = calibrationNumber,
    legId = legId,
    comment = comment,
    photos = photos
)

fun CathodicProtectionSurveyEntity.toDomainModel() = CathodicProtection(
    id = id,
    date = embedded.date,
    inspectorId = embedded.inspectorId,
    platformId = embedded.platformId,
    campaignId = embedded.campaignId,
    depthFortyReading = depthFortyReading,
    depthThirtyReading = depthThirtyReading,
    depthTwentyReading = depthTwentyReading,
    depthTenReading = depthTenReading,
    calibrationNumber = calibrationNumber,
    legId = legId,
    comment = comment,
    photos = photos
)
