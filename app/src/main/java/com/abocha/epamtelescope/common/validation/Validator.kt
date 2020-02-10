package com.abocha.epamtelescope.common.validation

import com.abocha.epamtelescope.common.validation.rules.Rule

/**
 * Created by Oleg Sheliakin on 2020-01-09.
 * Contact me by email - olegsheliakin@gmail.com
 */
class Validator<V>(private val valueGetter: () -> V) {

    /*
     * In case of buildValidation errorEvent or failure, this callback is invoked
     */
    private var errorCallback: ((messageResId: Int?) -> Unit)? = null

    /*
     * In case of buildValidation success, this callback is invoked
     */
    private var successCallback: (() -> Unit)? = null

    /*
     * The rules list to check for buildValidation
     */
    private var rulesList = arrayListOf<Rule<V>>()

    /*
     * Performs the buildValidation check and returns true or false.
     * Also invokes success and errorEvent callbacks if non null.
     */
    fun validate(): Boolean {
        for (rule in rulesList) {
            if (!rule.validate(valueGetter.invoke())) {
                errorCallback?.invoke(rule.messageResId)
                return false
            }
        }
        successCallback?.invoke()
        return true
    }

    fun anyError(): Int? {
        for (rule in rulesList) {
            if (!rule.validate(valueGetter.invoke())) {
                return rule.messageResId
            }
        }
        return null
    }

    fun addRule(vararg rule: Rule<V>): Validator<V> {
        rulesList.addAll(rule)
        return this
    }

    fun addRule(messageResId: Int, validate: (V) -> Boolean): Validator<V> {
        rulesList.add(
            InFlightRule(
                messageResId,
                validate
            )
        )
        return this
    }

    fun onError(callback: (messageResId: Int?) -> Unit): Validator<V> {
        errorCallback = callback
        return this
    }

    fun onSuccess(callback: () -> Unit): Validator<V> {
        successCallback = callback
        return this
    }

    private class InFlightRule<V>(
        override val messageResId: Int,
        private val validate: (V) -> Boolean
    ) : Rule<V> {
        override fun validate(input: V): Boolean =
            validate.invoke(input)

    }

}
