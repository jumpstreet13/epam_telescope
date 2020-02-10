package com.abocha.epamtelescope.common.validation

/**
 * Created by Oleg Sheliakin on 2020-01-09.
 * Contact me by email - olegsheliakin@gmail.com
 */
fun String.validator(): Validator<String> =
    Validator { this }

fun Any?.validator(): Validator<Any?> =
    Validator { this }
