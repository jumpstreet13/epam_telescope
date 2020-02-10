package com.abocha.epamtelescope.rxerroradapter

import retrofit2.adapter.rxjava2.HttpException
import kotlin.reflect.KClass

/**
 * Created by Oleg Sheliakin on 2019-12-23.
 * Contact me by email - olegsheliakin@gmail.com
 */
class ErrorAdapterProvider private constructor() {

    private val mapOfAdapters: MutableMap<KClass<out Throwable>, ErrorAdapter<Throwable>> by lazy {
        return@lazy mutableMapOf<KClass<out Throwable>, ErrorAdapter<Throwable>>()
    }

    companion object {
        fun create(builder: ErrorAdapterProvider.() -> Unit): ErrorAdapterProvider {
            return ErrorAdapterProvider().apply {
                builder.invoke(this)
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Throwable> register(klazz: KClass<T>, adapter: ErrorAdapter<T>): ErrorAdapterProvider {
        mapOfAdapters[klazz] = adapter as ErrorAdapter<Throwable>
        return this
    }

    @Suppress("UNCHECKED_CAST")
    fun registerHttpExceptionAdapter(adapter: HttpExceptionAdapter): ErrorAdapterProvider {
        mapOfAdapters[HttpException::class] = adapter as ErrorAdapter<Throwable>
        return this
    }

    internal fun provide(throwable: Throwable): ErrorAdapter<Throwable>? =
        mapOfAdapters[throwable::class]

}
