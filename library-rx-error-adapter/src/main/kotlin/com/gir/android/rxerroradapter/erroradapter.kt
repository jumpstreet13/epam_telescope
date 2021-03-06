package com.abocha.epamtelescope.rxerroradapter

/**
 * Created by Oleg Sheliakin on 2019-12-23.
 * Contact me by email - olegsheliakin@gmail.com
 */
typealias ErrorAdapter<T> = (T) -> Throwable?

object Identity : ErrorAdapter<Throwable> {
    override fun invoke(error: Throwable): Throwable? = error
}

fun <T : Throwable> ErrorAdapter<T>.asChain(): ErrorAdaptersChain<T> = ErrorAdaptersChain.create {
    addNext(this@asChain)
}

class ErrorAdapterChain<T : Throwable> private constructor(
    private val errorAdapter: ErrorAdapter<T>
) : ErrorAdapter<T> {

    private var next: ErrorAdapter<T>? = null

    companion object {
        fun <T : Throwable> create(errorAdapter: ErrorAdapter<T>) = ErrorAdapterChain(errorAdapter)
    }

    fun addNext(errorAdapter: ErrorAdapter<T>): ErrorAdapterChain<T> {
        this.next = errorAdapter
        return ErrorAdapterChain(errorAdapter)
    }

    override fun invoke(t: T): Throwable? =
        errorAdapter.invoke(t) ?: next?.invoke(t)

}
