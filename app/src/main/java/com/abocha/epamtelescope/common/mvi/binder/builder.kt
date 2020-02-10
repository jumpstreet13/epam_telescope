package com.abocha.epamtelescope.common.mvi.binder

/**
 * Created by Oleg Sheliakin on 2020-01-30.
 * Contact me by email - olegsheliakin@gmail.com
 */
inline fun <S : State> stateBinder(init: StateBinder.Builder<S>.() -> Unit): StateBinder<S> =
    StateBinder.Builder<S>()
        .apply(init)
        .build()
