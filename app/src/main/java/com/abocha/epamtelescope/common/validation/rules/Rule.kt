package com.abocha.epamtelescope.common.validation.rules

import androidx.annotation.StringRes

/**
 * Created by Oleg Sheliakin on 2019-12-25.
 * Contact me by email - olegsheliakin@gmail.com
 */
interface Rule<in V> {
    @get:StringRes
    val messageResId: Int

    fun validate(input: V): Boolean
}
