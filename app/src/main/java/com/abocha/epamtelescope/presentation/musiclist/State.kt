package com.abocha.epamtelescope.presentation.musiclist

/**
 * @author Magomedov Abakar
 */
data class State(
    val isLoading: Boolean = false,
    val songs: List<SongAdapterViewType> = emptyList(),
    val isEmpty: Boolean = false
)