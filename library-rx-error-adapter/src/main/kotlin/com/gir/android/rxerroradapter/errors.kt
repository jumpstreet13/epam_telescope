package com.abocha.epamtelescope.rxerroradapter

import com.abocha.epamtelescope.rxerroradapter.utils.EmptyStackThrowable

/**
 * Created by Oleg Sheliakin on 2019-12-23.
 * Contact me by email - olegsheliakin@gmail.com
 */
interface HttpError

data class HttpResponseError(
    val httpCode: Int,
    override val message: String,
    val errorBodyString: String?
) : EmptyStackThrowable(), HttpError
