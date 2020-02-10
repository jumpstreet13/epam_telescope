package com.abocha.epamtelescope.common.validation.rules

import com.abocha.epamtelescope.R

/**
 * Created by Oleg Sheliakin on 2019-12-25.
 * Contact me by email - olegsheliakin@gmail.com
 */
class IntRule(override val messageResId: Int = R.string.all_error_invalid_number) :
    Rule<String> {

    override fun validate(input: String): Boolean =
        input.toIntOrNull() != null

}
