package com.abocha.epamtelescope.data.network.models.responses

import com.abocha.epamtelescope.data.database.entities.SongEntity
import com.squareup.moshi.Json

/**
 * @author Magomedov Abakar
 */

data class TrackResponseData(
    @field:Json(name = "data")
    val trackList: List<TrackResponse> = emptyList()
)

data class TrackResponse(
    @field:Json(name = "id")
    val id: Long,
    @field:Json(name = "title")
    val songTitle: String
)

fun TrackResponse.toDbEntity(): SongEntity =
    SongEntity(
        id = id,
        songTitle = songTitle
    )

fun List<TrackResponse>.toDbEntities(): List<SongEntity> =
    map { it.toDbEntity() }
