package com.abocha.epamtelescope.common.di

/**
 * Created by Oleg Sheliakin on 2019-12-24.
 * Contact me by email - olegsheliakin@gmail.com
 */
interface InjectionComponent<in T> {
    fun inject(target: T)
}
