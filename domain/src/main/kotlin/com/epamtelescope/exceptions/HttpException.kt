package com.abocha.epamtelescope.exceptions

import java.util.*

/**
 * Created by Oleg Sheliakin on 2020-01-09.
 * Contact me by email - olegsheliakin@gmail.com
 */
data class HttpException(val httpCode: Int, override val message: String) : EmptyStackException()
