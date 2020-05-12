package com.wishbox.core.presentation.api.pagination

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abocha.epamtelescope.R
import com.abocha.epamtelescope.common.inflate
import com.abocha.epamtelescope.presentation.base.rx.click
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.wishbox.core.presentation.api.adapter.CanDispose
import io.reactivex.disposables.Disposable
import kotlinx.android.extensions.LayoutContainer

/**
 * @author Magomedov Abakar
 */

class PaginationErrorAdapterDelegate<I : T, T>(
    private val isForViewType: (item: T) -> Boolean,
    private val errorClick: (Unit) -> Unit
) : AbsListItemAdapterDelegate<I, T, PaginationErrorAdapterDelegate.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder =
        ViewHolder(parent.inflate(R.layout.item_pagination_error), errorClick)

    override fun isForViewType(item: T, items: MutableList<T>, position: Int): Boolean =
        isForViewType.invoke(item)

    override fun onBindViewHolder(item: I, holder: ViewHolder, payloads: MutableList<Any>) {
        holder.bind()
    }

    class ViewHolder(
        override val containerView: View,
        private val errorClick: (Unit) -> Unit
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer, CanDispose {

        private var disposable: Disposable? = null

        override fun dispose() {
            disposable?.dispose()
        }

        fun bind() {
            itemView.click()
                .subscribe(errorClick)
                .also { disposable = it }
        }
    }
}