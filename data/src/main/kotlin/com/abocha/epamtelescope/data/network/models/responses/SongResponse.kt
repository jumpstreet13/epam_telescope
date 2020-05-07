package com.abocha.epamtelescope.data.network.models.responses

import com.abocha.epamtelescope.data.database.entities.SongEntity
import com.squareup.moshi.Json

/**
 * @author Magomedov Abakar
 */
data class SongResponse(
    @field:Json(name = "song_url")
    val songUrl: String
)

fun SongResponse.toDbEntity(): SongEntity =
    SongEntity(
        songUrl = songUrl
    )

fun List<SongResponse>.toDbEntities(): List<SongEntity> =
    map { it.toDbEntity() }
