package com.abocha.epamtelescope.data.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.abocha.epamtelescope.entities.Well
import com.abocha.epamtelescope.entities.WellSlot

/**
 * Created by Oleg Sheliakin on 2020-01-14.
 * Contact me by email - olegsheliakin@gmail.com
 */
@Entity(
    tableName = WellSlotEntity.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = PlatformEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("platformId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class WellSlotEntity(
    @PrimaryKey
    val id: Long,
    val platformId: Long,
    val name: String,
    val wellId: Long,
    val wellName: String
) {
    companion object {
        const val TABLE_NAME = "well_slot_entity"
    }
}


fun WellSlotEntity.toDomainModel(): WellSlot =
    WellSlot(id = id, name = name, well = Well(wellId, wellName))

fun List<WellSlotEntity>.toDomainModels(): List<WellSlot> =
    map { it.toDomainModel() }

fun WellSlot.toDbEntity(platformId: Long): WellSlotEntity =
    WellSlotEntity(
        id = id,
        name = name,
        platformId = platformId,
        wellName = well.name,
        wellId = well.id
    )

fun List<WellSlot>.toDbEntities(platformId: Long): List<WellSlotEntity> =
    map { it.toDbEntity(platformId) }

