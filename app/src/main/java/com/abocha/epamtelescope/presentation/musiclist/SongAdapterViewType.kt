package com.abocha.epamtelescope.presentation.musiclist

/**
 * @author Magomedov Abakar
 */
sealed class SongAdapterViewType {

    data class Song(
        val id: Long,
        val songTitle: String,
        val songUrl: String,
        val songDuration: Int = 0,
        val currentSongProgress: Int = 0
    ) : SongAdapterViewType()

    object Loading : SongAdapterViewType()

    object Error : SongAdapterViewType()
}