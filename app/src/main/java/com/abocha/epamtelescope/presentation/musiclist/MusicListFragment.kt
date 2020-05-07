package com.abocha.epamtelescope.presentation.musiclist

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.abocha.epamtelescope.R
import com.abocha.epamtelescope.common.hideKeyboard
import com.abocha.epamtelescope.presentation.base.BaseFragment
import com.abocha.epamtelescope.presentation.musiclist.di.MusicListComponent
import kotlinx.android.synthetic.main.fragment_music_list.*
import javax.inject.Inject

/**
 * @author Magomedov Abakar
 */
class MusicListFragment : BaseFragment(R.layout.fragment_music_list) {

    @Inject
    lateinit var musicListAdapter: MusicListAdapter

    private val viewModel: MusicListViewModel by viewModels(factoryProducer = { viewModelFactory })

    override fun inject() {
        MusicListComponent(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.hideKeyboard()
        setupView()
        bindViewModel()
    }

    private fun setupView() {
        swipeToRefresh.setOnRefreshListener {
            viewModel.dispatch(Action.Refresh)
        }

        rvSongs.apply {
            adapter = musicListAdapter
            layoutManager = LinearLayoutManager(context)
        }

        musicListAdapter.songClicks = {
            viewModel.dispatch(Action.PlayMusic(it.songUrl))
        }
    }


    private fun bindViewModel() {
        viewModel.state.observeOnView {
            swipeToRefresh.isRefreshing = it.isLoading
            emptyText.isVisible = it.isEmpty
            musicListAdapter.submitList(it.songUrls)
        }
    }
}
