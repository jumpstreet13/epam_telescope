package com.abocha.epamtelescope.data.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.abocha.epamtelescope.data.database.entities.RiserClampSurveyEntity.Companion.TABLE_NAME
import com.abocha.epamtelescope.entities.ClampData
import com.abocha.epamtelescope.entities.EMPTY_ID
import com.abocha.epamtelescope.entities.RiserClamp
import com.abocha.epamtelescope.entities.SyncState

/**
 * Created by Oleg Sheliakin on 2020-01-28.
 * Contact me by email - olegsheliakin@gmail.com
 */

@Entity(tableName = TABLE_NAME)
data class RiserClampSurveyEntity(
    @PrimaryKey(autoGenerate = true)
    override val id: Long = EMPTY_ID,
    @Embedded
    override val embedded: EmbeddedSurveyEntity,
    val drawingReference: Int?,
    val clamps: List<ClampData>
) : SurveyEntity {

    companion object {
        const val TABLE_NAME = "riser_clamps_entity"
    }
}

fun RiserClampSurveyEntity.toDomainModel() =
    RiserClamp(
        id = id,
        date = embedded.date,
        inspectorId = embedded.inspectorId,
        platformId = embedded.platformId,
        campaignId = embedded.campaignId,
        drawingReference = drawingReference,
        clamps = clamps
    )

fun RiserClamp.toDbModel(syncState: SyncState) =
    RiserClampSurveyEntity(
        id = id,
        embedded = EmbeddedSurveyEntity(
            date = date,
            syncState = syncState,
            inspectorId = inspectorId,
            platformId = platformId,
            campaignId = campaignId
        ),
        drawingReference = drawingReference,
        clamps = clamps
    )
