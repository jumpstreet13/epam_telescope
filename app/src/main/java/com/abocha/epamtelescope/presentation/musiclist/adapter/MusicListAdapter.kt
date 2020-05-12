package com.abocha.epamtelescope.presentation.musiclist.adapter

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import com.abocha.epamtelescope.presentation.musiclist.SongAdapterViewType
import com.abocha.epamtelescope.presentation.musiclist.adapter.delegates.MusicListAdapterDelegate
import com.chauthai.swipereveallayout.ViewBinderHelper
import com.wishbox.core.presentation.api.adapter.AutoDisposeAsyncListDifferDelegationAdapter
import com.wishbox.core.presentation.api.adapter.DelegateWithInstanceState
import com.wishbox.core.presentation.api.pagination.PaginationErrorAdapterDelegate
import com.wishbox.core.presentation.api.pagination.PaginationProgressAdapterDelegate

/**
 * @author Magomedov Abakar
 */
class MusicListAdapter(
    bindViewHelper: ViewBinderHelper,
    songClicks: (SongAdapterViewType.Song) -> Unit,
    errorClick: (Unit) -> Unit
) : AutoDisposeAsyncListDifferDelegationAdapter<SongAdapterViewType>(SongsDiffUtil) {

    init {
        delegatesManager
            .addDelegate(MusicListAdapterDelegate(bindViewHelper, songClicks))
            .addDelegate(PaginationProgressAdapterDelegate<SongAdapterViewType.Loading, SongAdapterViewType> { it is SongAdapterViewType.Loading })
            .addDelegate(
                PaginationErrorAdapterDelegate<SongAdapterViewType.Error, SongAdapterViewType>(
                    isForViewType = { it is SongAdapterViewType.Error },
                    errorClick = errorClick
                )
            )
    }

    fun onSaveInstanceState(outState: Bundle?) {
        getDelegateForState(0)?.onSaveInstanceState(outState)
    }

    fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        getDelegateForState(0)?.onRestoreInstanceState(savedInstanceState)
    }

    private fun getDelegateForState(viewType: Int): DelegateWithInstanceState? =
        delegatesManager.getDelegateForViewType(viewType) as? DelegateWithInstanceState

    private object SongsDiffUtil : DiffUtil.ItemCallback<SongAdapterViewType>() {

        override fun areItemsTheSame(
            oldItem: SongAdapterViewType,
            newItem: SongAdapterViewType
        ): Boolean {
            if (oldItem.javaClass != newItem.javaClass) return false
            return when (oldItem) {
                is SongAdapterViewType.Song -> oldItem.id == (newItem as SongAdapterViewType.Song).id
                SongAdapterViewType.Loading -> true
                SongAdapterViewType.Error -> true
            }
        }

        override fun areContentsTheSame(
            oldItem: SongAdapterViewType,
            newItem: SongAdapterViewType
        ): Boolean = oldItem == newItem
    }
}