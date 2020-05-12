package com.abocha.epamtelescope.presentation.musiclist.di

import androidx.lifecycle.ViewModel
import com.abocha.epamtelescope.common.di.PerFragment
import com.abocha.epamtelescope.common.di.ViewModelKey
import com.abocha.epamtelescope.presentation.musiclist.Action
import com.abocha.epamtelescope.presentation.musiclist.MusicListViewModel
import com.abocha.epamtelescope.presentation.musiclist.adapter.MusicListAdapter
import com.chauthai.swipereveallayout.ViewBinderHelper
import com.wishbox.core.presentation.api.pagination.Paginator
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

/**
 * @author Magomedov Abakar
 */
@Module
interface MusicListModule {

    @Binds
    @IntoMap
    @ViewModelKey(MusicListViewModel::class)
    fun provideViewModel(impl: MusicListViewModel): ViewModel

    @Module
    companion object {

        @Provides
        fun provideAdapter(
            paginator: Paginator,
            viewModel: MusicListViewModel,
            binderHelper: ViewBinderHelper
        ): MusicListAdapter =
            MusicListAdapter(
                errorClick = { paginator.forceLoad(false) },
                songClicks = { viewModel.dispatch(Action.PlayMusic(it.songTitle)) },
                bindViewHelper = binderHelper
            )

        @Provides
        @PerFragment
        fun providePaginationScrollDelegate(viewModel: MusicListViewModel): Paginator =
            Paginator { viewModel.dispatch(Action.Refresh) }

        @Provides
        @PerFragment
        fun bindViewBinderHelper(): ViewBinderHelper =
            ViewBinderHelper()
                .apply { setOpenOnlyOne(true) }
    }
}