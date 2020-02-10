package com.abocha.epamtelescope.common.validation.rules

import android.util.Patterns

/**
 * Created by Oleg Sheliakin on 2019-12-25.
 * Contact me by email - olegsheliakin@gmail.com
 */
class EmailRule(override val messageResId: Int) :
    Rule<String> {

    override fun validate(input: String): Boolean =
        Patterns.EMAIL_ADDRESS.toRegex().matches(input)

}
