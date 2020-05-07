package com.abocha.epamtelescope.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.abocha.epamtelescope.data.database.entities.SongEntity.Companion.TABLE_NAME
import com.epamtelescope.entities.Song

/**
 * @author Magomedov Abakar
 */
@Entity(tableName = TABLE_NAME)
data class SongEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val songUrl: String
) {
    companion object {
        const val TABLE_NAME = "song_entity"
    }
}

fun SongEntity.toDomainModel(): Song =
    Song(
        songUrl = songUrl
    )

fun List<SongEntity>.toDomainModels(): List<Song> =
    map { it.toDomainModel() }