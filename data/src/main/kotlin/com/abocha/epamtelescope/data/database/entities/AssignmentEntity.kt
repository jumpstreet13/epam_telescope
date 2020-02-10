package com.abocha.epamtelescope.data.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.abocha.epamtelescope.data.database.entities.AssignmentEntity.Companion.TABLE_NAME
import com.abocha.epamtelescope.entities.Assignment
import com.abocha.epamtelescope.entities.InspectionCampaign
import org.threeten.bp.ZonedDateTime

/**
 * Created by Oleg Sheliakin on 2020-01-14.
 * Contact me by email - olegsheliakin@gmail.com
 */
@Entity(tableName = TABLE_NAME)
data class AssignmentEntity(
    @PrimaryKey
    val id: Long,
    val createdAt: ZonedDateTime,
    val modifiedAt: ZonedDateTime,
    @Embedded
    val inspectionCampaign: InspectionCampaign
) {
    companion object {
        const val TABLE_NAME = "assignment_entity"

    }
}

fun AssignmentEntity.toDomainModel(): Assignment =
    Assignment(
        id = id,
        createdAt = createdAt,
        modifiedAt = modifiedAt,
        inspectionCampaign = inspectionCampaign
    )

fun List<AssignmentEntity>.toDomainModels(): List<Assignment> =
    map { it.toDomainModel() }
