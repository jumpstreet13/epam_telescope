package com.abocha.epamtelescope.presentation.musiclist

/**
 * @author Magomedov Abakar
 */
sealed class SongAdapterViewType {

    data class Song(
        val id: Long,
        val songTitle: String
    ) : SongAdapterViewType()

    object Loading : SongAdapterViewType()

    object Error : SongAdapterViewType()
}