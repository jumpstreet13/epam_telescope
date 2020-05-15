package com.abocha.epamtelescope.presentation.musiclist.di

import android.content.Context
import androidx.lifecycle.ViewModel
import com.abocha.epamtelescope.common.di.PerFragment
import com.abocha.epamtelescope.common.di.ViewModelKey
import com.abocha.epamtelescope.presentation.musiclist.Action
import com.abocha.epamtelescope.presentation.musiclist.MusicListViewModel
import com.abocha.epamtelescope.presentation.musiclist.adapter.MusicListAdapter
import com.chauthai.swipereveallayout.ViewBinderHelper
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.audio.AudioAttributes
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
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
                songClicks = { viewModel.dispatch(Action.PlayMusic(it.songUrl)) },
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

        @Provides
        fun provideDefaultExoPlayer(context: Context): ExoPlayer =
            ExoPlayerFactory.newSimpleInstance(
                DefaultRenderersFactory(context)
                , DefaultTrackSelector(),
                DefaultLoadControl()
            ).apply {
                // AudioAttributes here from exoplayer package !!!
                val attr = AudioAttributes.Builder().setUsage(C.USAGE_MEDIA)
                    .setContentType(C.CONTENT_TYPE_MUSIC)
                    .build()
                audioAttributes = attr
            }

        @Provides
        fun provideExtractorMediaSourceFactory(context: Context): ExtractorMediaSource.Factory {
            val userAgent = Util.getUserAgent(context, "Exo")
            return ExtractorMediaSource.Factory(DefaultDataSourceFactory(context, userAgent))
                .setExtractorsFactory(DefaultExtractorsFactory())
        }
    }
}