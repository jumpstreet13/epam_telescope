package com.abocha.epamtelescope.entities

/**
 * Created by Oleg Sheliakin on 2020-01-23.
 * Contact me by email - olegsheliakin@gmail.com
 */
data class ClampData(
    val id: Long,
    val number: Int,
    val hasGasket: Boolean,
    val issue: String?,
    val clampElevation: Int?,
    val clampType: Dictionary?,
    val riserType: Dictionary?,
    val photoUrl: String?
)
