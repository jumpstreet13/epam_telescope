package com.abocha.epamtelescope.common.mvi.binder

import java.util.*

/**
 * Created by Oleg Sheliakin on 2020-01-30.
 * Contact me by email - olegsheliakin@gmail.com
 */
typealias ValueSelector<T, V> = (T) -> V

typealias BindAction<V> = (V) -> Unit

/**
 * Created by Oleg Sheliakin on 22.03.2019.
 * Contact me by email - olegsheliakin@gmail.com
 */
class StateBinder<T : State> private constructor(private val actions: List<Binder<T, Any?>>) {

    private var prev: Any = EMPTY
    private var current: Any = EMPTY

    /**
     * It applies current state even if the previous one is the same.
     */
    fun applyCurrentState() {
        prev = EMPTY
        applyState()
    }

    /**
     * Applies new state
     */
    fun newState(state: T) {
        prev = this.current
        current = state
        applyState()
    }

    private fun applyState() {
        actions.forEach {
            diffNullable(it.valueSelector, it.differ, it.action)
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun <V> diffInternal(selector: (T) -> V?, action: (V?, V?) -> Unit) {
        val localPrev = prev
        val localCurrent = current
        val prevValue = if (localPrev == EMPTY) null else selector(localPrev as T)
        val currentValue = if (localCurrent == EMPTY) null else selector(localCurrent as T)
        action(prevValue, currentValue)
    }

    private fun <V> diffNullable(
        selector: ValueSelector<T, V?>,
        differ: Differ<V?>,
        action: BindAction<V?>
    ) {
        diffInternal(selector) { prevValue, currentValue ->
            if (prev == EMPTY || differ.invoke(prevValue, currentValue)) {
                action(currentValue)
            }
        }
    }

    private data class Binder<T, V>(
        val valueSelector: ValueSelector<T, V>,
        val differ: Differ<V>,
        val action: BindAction<V>
    )

    private object EMPTY

    @Suppress("UNCHECKED_CAST")
    class Builder<S : State> : DslBuilder {

        private val actions: LinkedList<Binder<S, Any?>> = LinkedList()

        fun <F> bind(
            selector: ValueSelector<S, F>,
            differ: Differ<F> = byValue(),
            bindAction: BindAction<F>
        ) {
            actions += Binder(
                selector,
                differ,
                bindAction
            ) as Binder<S, Any?>
        }

        fun <F> bind(selector: ValueSelector<S, F>, bindAction: BindAction<F>) {
            bind(selector, byValue(), bindAction)
        }

        fun build(): StateBinder<S> =
            StateBinder(actions)
    }

    @StateBinderDsl
    interface DslBuilder

}


@DslMarker
annotation class StateBinderDsl
