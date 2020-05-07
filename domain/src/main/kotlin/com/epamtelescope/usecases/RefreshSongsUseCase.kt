package com.epamtelescope.usecases

import com.epamtelescope.repository.SongGateway
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**
 * @author Magomedov Abakar
 */
class RefreshSongsUseCase @Inject constructor(
    private val songGateway: SongGateway
) {
    private val statusPublisher: PublishSubject<Status> = PublishSubject.create()

    fun execute(): Completable =
        songGateway.refresh()
            .doOnError { statusPublisher.onNext(Status.Error(it)) }
            .doOnSubscribe { statusPublisher.onNext(Status.Loading) }
            .doOnComplete { statusPublisher.onNext(Status.Success) }

    fun observeStatus(): Observable<Status> =
        statusPublisher.hide()

    sealed class Status {
        object Loading : Status()
        object Success : Status()
        data class Error(val throwable: Throwable) : Status()
    }
}