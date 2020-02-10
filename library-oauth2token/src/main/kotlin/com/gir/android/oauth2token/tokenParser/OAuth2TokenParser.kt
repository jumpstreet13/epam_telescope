package com.abocha.epamtelescope.oauth2token.tokenParser

import okhttp3.ResponseBody

/**
 * Created by Oleg Sheliakin on 2019-12-23.
 * Contact me by email - olegsheliakin@gmail.com
 */
interface OAuth2TokenParser {
    fun parseTokens(body: ResponseBody?): Pair<String?, String?>?
}
