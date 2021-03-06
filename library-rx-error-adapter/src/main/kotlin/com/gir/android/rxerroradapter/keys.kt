package com.abocha.epamtelescope.rxerroradapter

/**
 * Created by olegshelyakin on 03/04/2019.
 * Contact me by email - olegsheliakin@gmail.com
 */
typealias KeySelector = (HttpResponseError) -> Key

interface Key {
    fun generateKey(): String
}

data class IntKey(val code: Int) : Key {
    override fun generateKey(): String = code.toString()
}

data class StringKey(val code: String) : Key {
    override fun generateKey(): String = code
}

class HttpCodeKeySelector : KeySelector {
    override fun invoke(p1: HttpResponseError): Key =
        IntKey(p1.httpCode)

}
