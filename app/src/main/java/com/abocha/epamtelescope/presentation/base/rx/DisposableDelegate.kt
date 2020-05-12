package com.abocha.epamtelescope.presentation.base.rx

import io.reactivex.disposables.Disposable
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * @author Magomedov Abakar
 */

class DisposableDelegate : ReadWriteProperty<Any, Disposable?> {

    private var disposable: Disposable? = null

    override fun getValue(thisRef: Any, property: KProperty<*>): Disposable? {
        return disposable
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Disposable?) {
        disposable?.takeUnless { it.isDisposed }?.dispose()
        disposable = value
    }

}

fun disposableDelegate(): DisposableDelegate = DisposableDelegate()
