package com.abocha.epamtelescope.data.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.abocha.epamtelescope.entities.Dictionary
import com.abocha.epamtelescope.entities.EMPTY_ID
import com.abocha.epamtelescope.entities.Riser
import com.abocha.epamtelescope.entities.SyncState

/**
 * Created by Oleg Sheliakin on 2020-01-28.
 * Contact me by email - olegsheliakin@gmail.com
 */

@Entity(tableName = RiserSurveyEntity.TABLE_NAME)
data class RiserSurveyEntity(
    @PrimaryKey(autoGenerate = true)
    override val id: Long = EMPTY_ID,
    @Embedded
    override val embedded: EmbeddedSurveyEntity,
    val platfromArea: Dictionary?,
    val riserType: Dictionary?,
    val segmentNumber: Int?,
    val apirp14CName: String,
    val drawingId: Int?,
    val diamiter: Int?,
    val mechDamage: Dictionary?,
    val mechDamageComment: String,
    val coatingCondition: Dictionary?,
    val coatingConditionComment: String,
    val readingAboveFlange: String,
    val readingBellowFlange: String,
    val repairsComment: String,
    val hseComment: String,
    val isoFlangeAccess: String,
    val paintedOnTheRiser: String,
    val comments: String,
    val riserGuardPresent: Boolean,
    val flangeIsoKitInstalled: Boolean,
    val flangeIsoKitWorking: Boolean,
    val hseHazard: Boolean = false
) : SurveyEntity {

    companion object {
        const val TABLE_NAME = "riser_survey_entity"
    }
}

fun Riser.toDbModel(syncState: SyncState) = RiserSurveyEntity(
    id = id,
    embedded = EmbeddedSurveyEntity(
        syncState = syncState,
        date = date,
        inspectorId = inspectorId,
        platformId = platformId,
        campaignId = campaignId
    ),
    platfromArea = platfromArea,
    riserType = riserType,
    segmentNumber = segmentNumber,
    apirp14CName = apirp14CName,
    drawingId = drawingId,
    diamiter = diamiter,
    mechDamage = mechDamage,
    mechDamageComment = mechDamageComment,
    coatingCondition = coatingCondition,
    coatingConditionComment = coatingConditionComment,
    readingAboveFlange = readingAboveFlange,
    readingBellowFlange = readingBellowFlange,
    repairsComment = repairsComment,
    hseComment = hseComment,
    isoFlangeAccess = isoFlangeAccess,
    paintedOnTheRiser = paintedOnTheRiser,
    comments = comments,
    riserGuardPresent = riserGuardPresent,
    flangeIsoKitInstalled = flangeIsoKitInstalled,
    flangeIsoKitWorking = flangeIsoKitWorking,
    hseHazard = hseHazard
)

fun RiserSurveyEntity.toDomainModel() = Riser(
    id = id,
    date = embedded.date,
    inspectorId = embedded.inspectorId,
    platformId = embedded.platformId,
    campaignId = embedded.campaignId,
    platfromArea = platfromArea,
    riserType = riserType,
    segmentNumber = segmentNumber,
    apirp14CName = apirp14CName,
    drawingId = drawingId,
    diamiter = diamiter,
    mechDamage = mechDamage,
    mechDamageComment = mechDamageComment,
    coatingCondition = coatingCondition,
    coatingConditionComment = coatingConditionComment,
    readingAboveFlange = readingAboveFlange,
    readingBellowFlange = readingBellowFlange,
    repairsComment = repairsComment,
    hseComment = hseComment,
    isoFlangeAccess = isoFlangeAccess,
    paintedOnTheRiser = paintedOnTheRiser,
    comments = comments,
    riserGuardPresent = riserGuardPresent,
    flangeIsoKitInstalled = flangeIsoKitInstalled,
    flangeIsoKitWorking = flangeIsoKitWorking,
    hseHazard = hseHazard
)
