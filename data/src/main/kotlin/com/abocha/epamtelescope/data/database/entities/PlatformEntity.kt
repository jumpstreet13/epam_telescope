package com.abocha.epamtelescope.data.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.abocha.epamtelescope.entities.Platform

/**
 * Created by Oleg Sheliakin on 2020-01-14.
 * Contact me by email - olegsheliakin@gmail.com
 */
@Entity(
    tableName = PlatformEntity.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = AssignmentEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("assignmentId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class PlatformEntity(
    @PrimaryKey
    val id: Long,
    val assignmentId: Long,
    val title: String
) {
    companion object {
        const val TABLE_NAME = "platform_entity"
    }
}

fun PlatformEntity.toDomainModel(): Platform =
    Platform(id, title)

fun List<PlatformEntity>.toDomainModels(): List<Platform> =
    map { it.toDomainModel() }
