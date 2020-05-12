package com.abocha.epamtelescope.presentation.musiclist.adapter.delegates

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.abocha.epamtelescope.R
import com.abocha.epamtelescope.common.inflate
import com.abocha.epamtelescope.presentation.base.BaseViewHolder
import com.abocha.epamtelescope.presentation.musiclist.SongAdapterViewType
import com.chauthai.swipereveallayout.ViewBinderHelper
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.wishbox.core.presentation.api.adapter.DelegateWithInstanceState
import kotlinx.android.synthetic.main.item_song.*

/**
 * @author Magomedov Abakar
 */
class MusicListAdapterDelegate constructor(
    private val binderHelper: ViewBinderHelper,
    private val songClicks: (SongAdapterViewType.Song) -> Unit
) :
    AbsListItemAdapterDelegate<SongAdapterViewType.Song, SongAdapterViewType, MusicListAdapterDelegate.ViewHolder>(),
    DelegateWithInstanceState {

    override fun onSaveInstanceState(outState: Bundle?) {
        binderHelper.saveStates(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        binderHelper.restoreStates(savedInstanceState)
    }

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder =
        ViewHolder(parent.inflate(R.layout.item_song), songClicks)

    override fun isForViewType(
        item: SongAdapterViewType,
        items: MutableList<SongAdapterViewType>,
        position: Int
    ): Boolean = item is SongAdapterViewType.Song

    override fun onBindViewHolder(
        item: SongAdapterViewType.Song,
        holder: ViewHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class ViewHolder(
        containerView: View,
        private val songClicks: (SongAdapterViewType.Song) -> Unit
    ) : BaseViewHolder<SongAdapterViewType.Song>(containerView) {

        override fun bind(model: SongAdapterViewType.Song) {
            songName.text = model.songTitle
            containerView.setOnClickListener {
                if (adapterPosition >= 0) {
                    songClicks.invoke(model)
                }
            }
        }
    }
}