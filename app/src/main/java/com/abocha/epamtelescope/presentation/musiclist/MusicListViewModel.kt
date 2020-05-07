package com.abocha.epamtelescope.presentation.musiclist

import com.abocha.epamtelescope.common.errors.ErrorHandler
import com.abocha.epamtelescope.common.mvi.MviViewModel
import com.abocha.epamtelescope.common.navigation.Router
import com.abocha.epamtelescope.common.throttling
import com.epamtelescope.usecases.GetAllSongsUseCase
import com.epamtelescope.usecases.RefreshSongsUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.ofType
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

/**
 * @author Magomedov Abakar
 */
class MusicListViewModel @Inject constructor(
    private val router: Router,
    private val errorHandler: ErrorHandler,
    private val refreshSongsUseCase: RefreshSongsUseCase,
    getAllSongsUseCase: GetAllSongsUseCase
) : MviViewModel<State, Action>() {

    init {
        newState(State())

        compositeDisposable += actions
            .ofType<Action.PlayMusic>()
            .throttling()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy {
            }

       /* compositeDisposable += getAllSongsUseCase
            .execute()
            .toObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError(errorHandler::handleError)
            .subscribeBy {
                newState(State(songUrls = it, isLoading = false, isEmpty = it.isEmpty()))
            }*/

        compositeDisposable += actions.ofType<Action.Refresh>()
            .switchMapCompletable {
                refreshSongsUseCase
                    .execute()
                    .onErrorComplete()
            }
            .subscribe()

        compositeDisposable += refreshSongsUseCase.observeStatus()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy { status ->
                when (status) {
                    RefreshSongsUseCase.Status.Loading -> newState {
                        it.copy(isLoading = it.songUrls.isEmpty(), isEmpty = false)
                    }
                    RefreshSongsUseCase.Status.Success -> newState {
                        it.copy(isLoading = false, isEmpty = it.songUrls.isEmpty())
                    }
                    is RefreshSongsUseCase.Status.Error -> {
                        errorHandler.handleError(status.throwable)
                        newState {
                            it.copy(isLoading = false)
                        }
                    }
                }
            }
    }
}