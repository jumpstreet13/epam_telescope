package com.abocha.epamtelescope.rxerroradapter

import retrofit2.CallAdapter
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.lang.reflect.Type

/**
 * Created by Oleg Sheliakin on 2019-12-23.
 * Contact me by email - olegsheliakin@gmail.com
 */
class RxErrorCallAdapterFactory private constructor(
    private val delegate: RxJava2CallAdapterFactory,
    private val errorAdapterProvider: ErrorAdapterProvider
) : CallAdapter.Factory() {

    companion object {
        fun create(
            delegate: RxJava2CallAdapterFactory,
            httpExceptionAdapter: ErrorAdapter<HttpException>
        ): RxErrorCallAdapterFactory {

            val errorAdapterProvider = ErrorAdapterProvider.create {
                register(retrofit2.adapter.rxjava2.HttpException::class, httpExceptionAdapter::invoke)
            }

            return RxErrorCallAdapterFactory(delegate, errorAdapterProvider)
        }

        fun create(
            delegate: RxJava2CallAdapterFactory,
            errorAdapterProvider: ErrorAdapterProvider
        ): RxErrorCallAdapterFactory =
            RxErrorCallAdapterFactory(delegate, errorAdapterProvider)

    }

    @Suppress("UNCHECKED_CAST")
    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<Any, Any>? {
        val callAdapter = delegate.get(returnType, annotations, retrofit) as? CallAdapter<in Any, out Any>
        return if (callAdapter == null) {
            null
        } else RxErrorCallAdapter(
            callAdapter,
            errorAdapterProvider
        )
    }

}
