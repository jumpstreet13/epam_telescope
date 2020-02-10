package com.abocha.epamtelescope.entities

/**
 * Created by Oleg Sheliakin on 2020-02-04.
 * Contact me by email - olegsheliakin@gmail.com
 */
data class WellSlot(
    val id: Long,
    val name: String,
    val well: Well
)
