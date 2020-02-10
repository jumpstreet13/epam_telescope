package com.abocha.epamtelescope.exceptions

/**
 * Created by Oleg Sheliakin on 2019-12-24.
 * Contact me by email - olegsheliakin@gmail.com
 */
data class ValidationException(val fields: Map<FieldType, String>) : Exception()
