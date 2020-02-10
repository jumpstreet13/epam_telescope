package com.abocha.epamtelescope.data.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.abocha.epamtelescope.entities.Component
import com.abocha.epamtelescope.entities.Dictionary
import com.abocha.epamtelescope.entities.EMPTY_ID
import com.abocha.epamtelescope.entities.SyncState

/**
 * Created by Oleg Sheliakin on 2020-01-28.
 * Contact me by email - olegsheliakin@gmail.com
 */

@Entity(tableName = ComponentSurveyEntity.TABLE_NAME)
data class ComponentSurveyEntity(
    @PrimaryKey(autoGenerate = true)
    override val id: Long = EMPTY_ID,
    @Embedded
    override val embedded: EmbeddedSurveyEntity,
    val componentType: Dictionary?,
    val platformArea: Dictionary?,
    val mechanicalDamage: Dictionary?,
    val mechanicalDamageComment: String,
    val coatingCondition: Dictionary?,
    val coatingConditionComment: String,
    val hseComment: String,
    val comment: String,
    val hseHazard: Boolean
) : SurveyEntity {

    companion object {
        const val TABLE_NAME = "component_survey_entity"
    }
}

fun Component.toDbModel(syncState: SyncState) = ComponentSurveyEntity(
    id = id,
    embedded = EmbeddedSurveyEntity(
        syncState = syncState,
        date = date,
        inspectorId = inspectorId,
        platformId = platformId,
        campaignId = campaignId
    ),
    componentType = componentType,
    coatingCondition = coatingCondition,
    platformArea = platformArea,
    hseComment = hseComment,
    coatingConditionComment = coatingConditionComment,
    hseHazard = hseHazard,
    comment = comment,
    mechanicalDamage = mechanicalDamage,
    mechanicalDamageComment = mechanicalDamageComment
)

fun ComponentSurveyEntity.toDomainModel() = Component(
    id = id,
    date = embedded.date,
    inspectorId = embedded.inspectorId,
    platformId = embedded.platformId,
    campaignId = embedded.campaignId,
    coatingCondition = coatingCondition,
    hseComment = hseComment,
    coatingConditionComment = coatingConditionComment,
    hseHazard = hseHazard,
    platformArea = platformArea,
    comment = comment,
    componentType = componentType,
    mechanicalDamage = mechanicalDamage,
    mechanicalDamageComment = mechanicalDamageComment
)
