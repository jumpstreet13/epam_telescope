package com.abocha.epamtelescope.rxerroradapter

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

/**
 * Created by Oleg Sheliakin on 2019-12-23.
 * Contact me by email - olegsheliakin@gmail.com
 */
internal class RxErrorCallAdapter constructor(
    private val wrapped: CallAdapter<in Any, out Any>,
    private val errorAdapterProvider: ErrorAdapterProvider
) : CallAdapter<Any, Any> {

    override fun responseType(): Type =
        wrapped.responseType()

    @Suppress("TooGenericExceptionThrown")
    override fun adapt(call: Call<Any>): Any =
        when (val adaptedCall = wrapped.adapt(call)) {
            is Completable -> adaptedCall.onErrorResumeNext { t: Throwable -> Completable.error(adapt(t)) }
            is Single<*> -> adaptedCall.onErrorResumeNext { t: Throwable -> Single.error(adapt(t)) }
            is Observable<*> -> adaptedCall.onErrorResumeNext { t: Throwable -> Observable.error(adapt(t)) }
            is Flowable<*> -> adaptedCall.onErrorResumeNext { t: Throwable -> Flowable.error(adapt(t)) }
            else -> throw RuntimeException("Type ${adaptedCall.javaClass.simpleName} is not supported")
        }

    private fun adapt(throwable: Throwable): Throwable {
        val adapter = errorAdapterProvider.provide(throwable)
        return adapter?.invoke(throwable) ?: throwable
    }

}
