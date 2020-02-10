package com.abocha.epamtelescope.data.sharedpreference

import android.content.SharedPreferences
import com.squareup.moshi.Moshi

interface JsonParser<T> {
    fun fromJson(json: String): T
    fun toJson(value: T): String
}

inline fun <reified T : Any> Moshi.asParser(): JsonParser<T> {
    return object : JsonParser<T> {
        override fun fromJson(json: String): T {
            return this@asParser.adapter(T::class.java).fromJson(json)
                ?: throw IllegalArgumentException("Not valid json")
        }

        override fun toJson(value: T): String {
            return this@asParser.adapter(T::class.java).toJson(value)
        }

    }
}

inline fun <reified T : Any> SharedPreferences.delegate(parser: JsonParser<T>) =
    SharedPrefDelegate(T::class.java.name, parser, this)
