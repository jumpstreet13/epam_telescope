package com.abocha.epamtelescope.data.sharedpreference

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class SharedPrefDelegate<T>(
    private val name: String,
    private val parser: JsonParser<T>,
    private val sharedPreferences: SharedPreferences
) : ReadWriteProperty<Any?, T?> {

    private var value: T? = null

    override fun getValue(thisRef: Any?, property: KProperty<*>): T? {
        if (value == null) {
            value = sharedPreferences.getString(name, null)?.let {  parser.fromJson(it) }
        }
        return value
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
        if (value != null) {
            sharedPreferences.edit().putString(name, parser.toJson(value)).apply()
        } else {
            sharedPreferences.edit().putString(name, null).apply()
        }
        this.value = value
    }
}
