package com.abocha.epamtelescope.oauth2token.session

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by Oleg Sheliakin on 2019-12-23.
 * Contact me by email - olegsheliakin@gmail.com
 */
interface OAuth2Session {
    var accessToken: String?
    var refreshToken: String?

    fun clearToken() {
        accessToken = null
        refreshToken = null
    }
}

class OAuth2SessionImpl(sharedPref: SharedPreferences) : OAuth2Session {
    override var accessToken: String? by PrefDelegate(PrefKey.ACCESS_TOKEN, sharedPref)
    override var refreshToken: String? by PrefDelegate(PrefKey.REFRESH_TOKEN, sharedPref)
}

private class PrefDelegate<T>(private val key: PrefKey,
                              private val sharedPref: SharedPreferences) : ReadWriteProperty<T, String?> {

    private var value: String? = null

    override fun getValue(thisRef: T, property: KProperty<*>): String? {
        if (value == null) {
            value = sharedPref.getString(key.key, null)
        }
        return value
    }

    override fun setValue(thisRef: T, property: KProperty<*>, value: String?) {
        if (value != null) {
            sharedPref.edit().putString(key.key, value).apply()
        } else {
            sharedPref.edit().remove(key.key).apply()
        }
        this.value = value
    }
}

private enum class PrefKey(val key: String) {
    ACCESS_TOKEN("PREF_ACCESS_TOKEN"),
    REFRESH_TOKEN("PREF_REFRESH_TOKEN")
}
