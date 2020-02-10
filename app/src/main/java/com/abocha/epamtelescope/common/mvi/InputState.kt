package com.abocha.epamtelescope.common.mvi

import com.abocha.epamtelescope.common.mvi.binder.State
import com.abocha.epamtelescope.exceptions.FieldType

/**
 * Created by Oleg Sheliakin on 2020-01-16.
 * Contact me by email - olegsheliakin@gmail.com
 */
data class InputState(
    val isLoading: Boolean = false,
    val login: String = "",
    val validationErrorMap: Map<FieldType, String> = emptyMap()
) : State
