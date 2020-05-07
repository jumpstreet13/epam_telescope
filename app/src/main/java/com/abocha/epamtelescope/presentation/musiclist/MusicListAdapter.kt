package com.abocha.epamtelescope.presentation.musiclist

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.abocha.epamtelescope.R
import com.abocha.epamtelescope.common.inflate
import com.abocha.epamtelescope.presentation.base.BaseViewHolder
import com.epamtelescope.entities.Song
import kotlinx.android.synthetic.main.item_song.*
import javax.inject.Inject

/**
 * @author Magomedov Abakar
 */
class MusicListAdapter @Inject constructor() : ListAdapter<Song, MusicListAdapter.ViewHolder>(SongsDiffUtil) {

    var songClicks: (Song) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent.inflate(R.layout.item_song))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(containerView: View) : BaseViewHolder<Song>(containerView) {

        init {
            containerView.setOnClickListener {
                if (adapterPosition >= 0) {
                    songClicks.invoke(getItem(adapterPosition))
                }
            }
        }

        override fun bind(model: Song) {
            songName.text = model.songUrl
        }
    }

    private object SongsDiffUtil : DiffUtil.ItemCallback<Song>() {
        override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean =
            oldItem.songUrl == newItem.songUrl

        override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean =
            oldItem == newItem
    }
}