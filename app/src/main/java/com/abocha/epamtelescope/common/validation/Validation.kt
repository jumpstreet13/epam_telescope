package com.abocha.epamtelescope.common.validation

/**
 * Created by Oleg Sheliakin on 2020-01-30.
 * Contact me by email - olegsheliakin@gmail.com
 */
class Validation {

    private val validators: MutableList<Validator<*>> = mutableListOf()

    fun add(validator: Validator<*>): Validation {
        validators.add(validator)
        return this
    }

    fun all(): Boolean =
        validators.map { it.validate() }.all { it }

}

fun buildValidation(builder: Validation.() -> Unit): Validation {
    val validation = Validation()
    builder.invoke(validation)
    return validation
}
