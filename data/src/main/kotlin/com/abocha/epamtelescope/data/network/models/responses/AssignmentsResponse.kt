package com.abocha.epamtelescope.data.network.models.responses

import com.abocha.epamtelescope.data.database.entities.AssignmentEntity
import com.abocha.epamtelescope.data.database.entities.PlatformEntity
import com.squareup.moshi.Json
import org.threeten.bp.ZonedDateTime

/**
 * Created by Oleg Sheliakin on 2020-01-15.
 * Contact me by email - olegsheliakin@gmail.com
 */
data class AssignmentsResponse(
    val id: Long,
    @field:Json(name = "inspection_campaign")
    val inspectionCampaign: InspectionCampaignResponse,
    val platforms: List<PlatformResponse>,
    @field:Json(name = "created_at")
    val createdAt: ZonedDateTime,
    @field:Json(name = "modified_at")
    val modifiedAt: ZonedDateTime
)

fun AssignmentsResponse.toDbEntity(): AssignmentEntity =
    AssignmentEntity(
        id = id,
        inspectionCampaign = inspectionCampaign.toDomainModel(),
        createdAt = createdAt,
        modifiedAt = modifiedAt
    )

fun PlatformResponse.toDbEntity(assignmentId: Long): PlatformEntity =
    PlatformEntity(id = id, title = title, assignmentId = assignmentId)

fun List<AssignmentsResponse>.toDbEntities(): List<AssignmentEntity> =
    map { it.toDbEntity() }

fun List<PlatformResponse>.toDbEntities(assignmentId: Long): List<PlatformEntity> =
    map { it.toDbEntity(assignmentId) }
