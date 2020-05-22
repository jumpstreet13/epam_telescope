package com.abocha.epamtelescope.presentation.musiclist.mapper

import android.content.Context
import com.abocha.epamtelescope.presentation.musiclist.SongAdapterViewType
import com.epamtelescope.entities.Song
import javax.inject.Inject

/**
 * @author Magomedov Abakar
 */
class MusicViewMapper @Inject constructor(
    private val context: Context
) {
    fun mapToViewTypeList(from: List<Song>): List<SongAdapterViewType> =
        from.map { mapToViewType(it) }

    private fun mapToViewType(from: Song): SongAdapterViewType =
        SongAdapterViewType.Song(
            id = from.id,
            songTitle = from.songTitle,
            songUrl = from.songUrl,
            songDuration = from.songDuration
        )
}