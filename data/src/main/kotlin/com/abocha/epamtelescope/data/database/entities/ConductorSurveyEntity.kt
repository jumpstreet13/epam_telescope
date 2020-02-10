package com.abocha.epamtelescope.data.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.abocha.epamtelescope.entities.ConductorWellHead
import com.abocha.epamtelescope.entities.Dictionary
import com.abocha.epamtelescope.entities.EMPTY_ID
import com.abocha.epamtelescope.entities.SyncState

/**
 * Created by Oleg Sheliakin on 2020-01-28.
 * Contact me by email - olegsheliakin@gmail.com
 */

@Entity(tableName = ConductorSurveyEntity.TABLE_NAME)
data class ConductorSurveyEntity(
    @PrimaryKey(autoGenerate = true)
    override val id: Long = EMPTY_ID,
    @Embedded
    override val embedded: EmbeddedSurveyEntity,
    val mechanicalDamage: Dictionary?,
    val mechanicalDamageComment: String,
    val coatingCondition: Dictionary?,
    val coatingConditionComment: String,
    val conductorArea: Dictionary?,
    val hseComment: String,
    val comment: String,
    val hseHazard: Boolean,
    val wellId: Long?,
    val slotId: Long?,
    val diameter: Int?,
    val gap: Int?,
    val shims: Boolean,
    val guideDamage: Boolean
) : SurveyEntity {

    companion object {
        const val TABLE_NAME = "conductor_survey_entity"
    }
}

fun ConductorWellHead.toDbModel(syncState: SyncState) = ConductorSurveyEntity(
    id = id,
    embedded = EmbeddedSurveyEntity(
        syncState = syncState,
        date = date,
        inspectorId = inspectorId,
        platformId = platformId,
        campaignId = campaignId
    ),
    coatingCondition = coatingCondition,
    hseComment = hseComment,
    coatingConditionComment = coatingConditionComment,
    hseHazard = hseHazard,
    conductorArea = conductorArea,
    diameter = diameter,
    comment = comment,
    gap = gap,
    guideDamage = guideDamage,
    mechanicalDamage = mechanicalDamage,
    mechanicalDamageComment = mechanicalDamageComment,
    shims = shims,
    slotId = wellSlot?.id,
    wellId = wellSlot?.well?.id
)

fun ConductorSurveyEntity.toDomainModel(wellSlotEntity: WellSlotEntity?) = ConductorWellHead(
    id = id,
    date = embedded.date,
    inspectorId = embedded.inspectorId,
    platformId = embedded.platformId,
    campaignId = embedded.campaignId,
    coatingCondition = coatingCondition,
    hseComment = hseComment,
    coatingConditionComment = coatingConditionComment,
    hseHazard = hseHazard,
    conductorArea = conductorArea,
    diameter = diameter,
    gap = gap,
    comment = comment,
    guideDamage = guideDamage,
    mechanicalDamage = mechanicalDamage,
    mechanicalDamageComment = mechanicalDamageComment,
    shims = shims,
    wellSlot = wellSlotEntity?.toDomainModel()
)
