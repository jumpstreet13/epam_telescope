package com.abocha.epamtelescope.presentation.musiclist

import com.abocha.epamtelescope.common.errors.ErrorHandler
import com.abocha.epamtelescope.common.mvi.MviViewModel
import com.abocha.epamtelescope.common.navigation.Router
import com.abocha.epamtelescope.common.throttling
import com.abocha.epamtelescope.presentation.musiclist.mapper.MusicViewMapper
import com.epamtelescope.usecases.ObserveAllSongsUseCase
import com.epamtelescope.usecases.RequestSongsUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.ofType
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * @author Magomedov Abakar
 */
class MusicListViewModel @Inject constructor(
    private val router: Router,
    private val errorHandler: ErrorHandler,
    private val refreshSongsUseCase: RequestSongsUseCase,
    private val mapper: MusicViewMapper,
    getAllSongsUseCase: ObserveAllSongsUseCase
) : MviViewModel<State, Action>() {

    init {
        newState(State())

        compositeDisposable += actions
            .ofType<Action.PlayMusic>()
            .throttling()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy {
            }

        compositeDisposable += getAllSongsUseCase
            .execute()
            .toObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError(errorHandler::handleError)
            .map { mapper.mapToViewTypeList(it) }
            .subscribeBy {
                newState(State(songs = it, isLoading = false, isEmpty = it.isEmpty()))
            }

        compositeDisposable += actions.ofType<Action.Refresh>()
            .switchMapCompletable {
                refreshSongsUseCase
                    .execute(true)
                    .onErrorComplete()
            }
            .subscribe()

        compositeDisposable += refreshSongsUseCase.observeStatus()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy { status ->
                when (status) {
                    RequestSongsUseCase.Status.Loading -> newState {
                        it.copy(isLoading = it.songs.isEmpty(), isEmpty = false,
                            songs = it.songs.toMutableList().apply {
                                add(SongAdapterViewType.Loading)
                            })
                    }
                    RequestSongsUseCase.Status.Success -> newState {
                        it.copy(isLoading = false, isEmpty = it.songs.isEmpty(),
                            songs = it.songs.toMutableList().apply {
                                remove(SongAdapterViewType.Loading)
                            })
                    }
                    is RequestSongsUseCase.Status.Error -> {
                        errorHandler.handleError(status.throwable)
                        newState {
                            it.copy(isLoading = false,
                                songs = it.songs.toMutableList().apply {
                                    remove(SongAdapterViewType.Loading)
                                })
                        }
                    }
                }
            }
    }
}