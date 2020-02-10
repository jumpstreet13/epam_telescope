package com.abocha.epamtelescope.common.mvi.binder

/**
 * Created by Oleg Sheliakin on 2020-01-30.
 * Contact me by email - olegsheliakin@gmail.com
 */
typealias Differ<V> = (V, V) -> Boolean

inline fun <T> byValue(): Differ<T> = { p1, p2 -> p2 != p1 }
inline fun <T> diffWith(value: T?): Differ<T?> = { _, p2 -> p2 != value }
inline fun <T> byRef(): Differ<T> = { p1, p2 -> p2 !== p1 }
