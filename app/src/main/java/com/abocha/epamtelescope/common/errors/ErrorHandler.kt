package com.abocha.epamtelescope.common.errors

/**
 * Created by Oleg Sheliakin on 2019-12-24.
 * Contact me by email - olegsheliakin@gmail.com
 */
interface ErrorHandler : HasErrors {
    fun handleError(error: Throwable)
}
