package com.abocha.epamtelescope.presentation.musiclist

import com.epamtelescope.entities.Song

/**
 * @author Magomedov Abakar
 */
data class State(
    val isLoading: Boolean = false,
    val songUrls: List<Song> = emptyList(),
    val isEmpty: Boolean = false
)