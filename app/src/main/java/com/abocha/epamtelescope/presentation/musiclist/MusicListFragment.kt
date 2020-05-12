package com.abocha.epamtelescope.presentation.musiclist

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.abocha.epamtelescope.R
import com.abocha.epamtelescope.common.hideKeyboard
import com.abocha.epamtelescope.presentation.base.BaseFragment
import com.abocha.epamtelescope.presentation.helper.ScrollingButtonHelper
import com.abocha.epamtelescope.presentation.musiclist.adapter.MusicListAdapter
import com.abocha.epamtelescope.presentation.musiclist.di.MusicListComponent
import com.wishbox.core.presentation.api.pagination.Paginator
import kotlinx.android.synthetic.main.fragment_music_list.*
import javax.inject.Inject

/**
 * @author Magomedov Abakar
 */
class MusicListFragment : BaseFragment(R.layout.fragment_music_list) {

    @Inject
    lateinit var musicListAdapter: MusicListAdapter

    @Inject
    lateinit var paginator: Paginator

    @Inject
    lateinit var scrollButtonHelper: ScrollingButtonHelper

    private val viewModel: MusicListViewModel by viewModels(factoryProducer = { viewModelFactory })

    override fun inject() {
        MusicListComponent(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.hideKeyboard()
        setupView()
        bindViewModel()
        viewModel.dispatch(Action.Refresh)
        //scrollButtonHelper.withRecyclerView(recyclerFriends, buttonAdd) example
    }

    private fun setupView() {
        swipeToRefresh.setOnRefreshListener {
            viewModel.dispatch(Action.Refresh)
        }

        rvSongs.apply {
            adapter = musicListAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        paginator.onSaveInstanceState(outState)
        musicListAdapter.onSaveInstanceState(outState)
        scrollButtonHelper.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        paginator.onViewStateRestored(savedInstanceState)
        musicListAdapter.onRestoreInstanceState(savedInstanceState)
        scrollButtonHelper.onViewStateRestored(savedInstanceState)
    }


    private fun bindViewModel() {
        viewModel.state.observeOnView {
            swipeToRefresh.isRefreshing = it.isLoading
            emptyText.isVisible = it.isEmpty
            musicListAdapter.items = it.songs
        }
    }
}
