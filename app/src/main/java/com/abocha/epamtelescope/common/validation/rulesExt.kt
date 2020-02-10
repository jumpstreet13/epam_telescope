package com.abocha.epamtelescope.common.validation

import com.abocha.epamtelescope.R
import com.abocha.epamtelescope.common.validation.rules.EmailRule
import com.abocha.epamtelescope.common.validation.rules.NotBlankRule

/**
 * Created by Oleg Sheliakin on 2020-01-09.
 * Contact me by email - olegsheliakin@gmail.com
 */

fun Validator<String>.nonBlank(errorMessageId: Int = R.string.all_error_blank_field): Validator<String> =
    addRule(NotBlankRule(errorMessageId))

fun Validator<String>.isEmail(errorMessageId: Int = R.string.all_error_invalid_email): Validator<String> =
    addRule(EmailRule(errorMessageId))
