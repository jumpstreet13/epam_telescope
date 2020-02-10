package com.abocha.epamtelescope.data.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.abocha.epamtelescope.entities.EMPTY_ID
import com.abocha.epamtelescope.entities.Isims
import com.abocha.epamtelescope.entities.SyncState

/**
 * Created by Oleg Sheliakin on 2020-01-28.
 * Contact me by email - olegsheliakin@gmail.com
 */

@Entity(tableName = IsimsSurveyEntity.TABLE_NAME)
data class IsimsSurveyEntity(
    @PrimaryKey(autoGenerate = true)
    override val id: Long = EMPTY_ID,
    @Embedded
    override val embedded: EmbeddedSurveyEntity,
    val anomalyNumber: Int?,
    val anomalyDescription: String
) : SurveyEntity {

    companion object {
        const val TABLE_NAME = "isims_survey_entity"
    }
}

fun Isims.toDbModel(syncState: SyncState) = IsimsSurveyEntity(
    id = id,
    embedded = EmbeddedSurveyEntity(
        syncState = syncState,
        date = date,
        inspectorId = inspectorId,
        platformId = platformId,
        campaignId = campaignId
    ),
    anomalyNumber = anomalyNumber,
    anomalyDescription = anomalyDescription
)

fun IsimsSurveyEntity.toDomainModel() = Isims(
    id = id,
    date = embedded.date,
    inspectorId = embedded.inspectorId,
    platformId = embedded.platformId,
    campaignId = embedded.campaignId,
    anomalyNumber = anomalyNumber,
    anomalyDescription = anomalyDescription
)
