package com.abocha.epamtelescope.common.mvi

/**
 * Created by Oleg Sheliakin on 2020-01-15.
 * Contact me by email - olegsheliakin@gmail.com
 */
typealias Reducer<S, C> = (state: S, change: C) -> S
