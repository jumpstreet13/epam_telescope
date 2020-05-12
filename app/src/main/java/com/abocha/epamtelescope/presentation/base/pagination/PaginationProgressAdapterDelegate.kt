package com.wishbox.core.presentation.api.pagination

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abocha.epamtelescope.R
import com.abocha.epamtelescope.common.inflate
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import kotlinx.android.extensions.LayoutContainer

/**
 * @author Magomedov Abakar
 */

class PaginationProgressAdapterDelegate<I : T, T>(
    private val isForViewType: (item: T) -> Boolean
) : AbsListItemAdapterDelegate<I, T, PaginationProgressAdapterDelegate.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder =
        ViewHolder(parent.inflate(R.layout.item_pagination_progress))

    override fun isForViewType(item: T, items: MutableList<T>, position: Int): Boolean =
        isForViewType.invoke(item)

    override fun onBindViewHolder(item: I, holder: ViewHolder, payloads: MutableList<Any>) {}

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer
}