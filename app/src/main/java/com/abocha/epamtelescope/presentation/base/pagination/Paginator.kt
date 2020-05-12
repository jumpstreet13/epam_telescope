package com.wishbox.core.presentation.api.pagination

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Magomedov Abakar
 */

class Paginator(
    private val scrollFunc: (isInitial: Boolean) -> Unit
) {

    companion object {
        const val LIMIT = 20

        private const val BUNDLE_ENABLE_STATE = "BUNDLE_ENABLE_STATE"
    }

    var isEnabled: Boolean = true

    fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(BUNDLE_ENABLE_STATE, isEnabled)
    }

    fun onViewStateRestored(savedInstanceState: Bundle?) {
        savedInstanceState?.getBoolean(BUNDLE_ENABLE_STATE)?.let { isEnabled = it }
    }

    val loadMargin = LIMIT / 2

    fun hasLoadPage() {
        isEnabled = true
    }

    fun withRecyclerView(recyclerView: RecyclerView, initialLoad: Boolean = true) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                recyclerView.layoutManager?.let {
                    val visibleItemCount = it.childCount
                    val totalItemCount = it.itemCount
                    val firstVisibleItemPosition = when (it) {
                        is LinearLayoutManager -> it.findFirstVisibleItemPosition()
                        is GridLayoutManager -> it.findFirstVisibleItemPosition()
                        else -> throw IllegalStateException("Not implemented yet")
                    }

                    if (totalItemCount - visibleItemCount <= firstVisibleItemPosition + loadMargin && firstVisibleItemPosition >= 0) {
                        load()
                    }
                } ?: throw IllegalArgumentException("LayoutManager not specified")
            }
        })
        if (initialLoad) {
            load(initialLoad)
        }
    }

    fun forceLoad(isInitial: Boolean) {
        scrollFunc.invoke(isInitial)
    }

    private fun load(isInitial: Boolean = false) {
        if (!(isInitial || isEnabled)) return
        isEnabled = false
        scrollFunc.invoke(isInitial)
    }
}