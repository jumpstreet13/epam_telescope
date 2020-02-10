package com.abocha.epamtelescope.data.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.abocha.epamtelescope.entities.CPCalibration
import com.abocha.epamtelescope.entities.EMPTY_ID
import com.abocha.epamtelescope.entities.SyncState

/**
 * Created by Oleg Sheliakin on 2020-01-28.
 * Contact me by email - olegsheliakin@gmail.com
 */

@Entity(tableName = CathodicProtectionCalibrationSurveyEntity.TABLE_NAME)
data class CathodicProtectionCalibrationSurveyEntity(
    @PrimaryKey(autoGenerate = true)
    override val id: Long = EMPTY_ID,
    @Embedded
    override val embedded: EmbeddedSurveyEntity,
    val calibrationNumber: Int?,
    val multimeterBrand: String,
    val multimeterSN: String,
    val preCalibration: Int?,
    val postCalibration: Int?
) : SurveyEntity {

    companion object {
        const val TABLE_NAME = "cathodic_protection_calibration_survey_entity"
    }
}

fun CPCalibration.toDbModel(syncState: SyncState) =
    CathodicProtectionCalibrationSurveyEntity(
        id = id,
        embedded = EmbeddedSurveyEntity(
            syncState = syncState,
            date = date,
            inspectorId = inspectorId,
            platformId = platformId,
            campaignId = campaignId
        ),
        preCalibration = preCalibration,
        postCalibration = postCalibration,
        multimeterSN = multimeterSN,
        multimeterBrand = multimeterBrand,
        calibrationNumber = calibrationNumber
    )

fun CathodicProtectionCalibrationSurveyEntity.toDomainModel() =
    CPCalibration(
        id = id,
        date = embedded.date,
        inspectorId = embedded.inspectorId,
        platformId = embedded.platformId,
        campaignId = embedded.campaignId,
        preCalibration = preCalibration,
        postCalibration = postCalibration,
        multimeterSN = multimeterSN,
        multimeterBrand = multimeterBrand,
        calibrationNumber = calibrationNumber
    )
