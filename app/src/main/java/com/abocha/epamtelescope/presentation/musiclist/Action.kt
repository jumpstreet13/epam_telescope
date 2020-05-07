package com.abocha.epamtelescope.presentation.musiclist

/**
 * @author Magomedov Abakar
 */
sealed class Action {
    object Refresh : Action()
    data class PlayMusic(val songUrl: String) : Action()
}