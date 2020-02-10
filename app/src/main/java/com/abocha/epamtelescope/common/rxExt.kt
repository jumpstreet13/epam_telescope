package com.abocha.epamtelescope.common

import io.reactivex.Observable
import java.util.concurrent.TimeUnit

/**
 * Created by Oleg Sheliakin on 2020-01-16.
 * Contact me by email - olegsheliakin@gmail.com
 */

private const val THROTTLE_TIME_WINDOW = 500L

fun <T> Observable<T>.throttling(): Observable<T> =
    take(1).concatWith(debounce(THROTTLE_TIME_WINDOW, TimeUnit.MILLISECONDS))
