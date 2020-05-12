package com.wishbox.core.presentation.api.adapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

/***
 * Делегат для автоматического диспоза подписок ViewHolder'ов
 */
abstract class AutoDisposeAsyncListDifferDelegationAdapter<T>(diffCallback: DiffUtil.ItemCallback<T>) :
    AsyncListDifferDelegationAdapter<T>(diffCallback) {

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        if (holder is CanDispose) holder.dispose()
        super.onViewRecycled(holder)
    }
}