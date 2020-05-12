package com.epamtelescope.usecases

import com.epamtelescope.repository.SongGateway
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**
 * @author Magomedov Abakar
 */
class RequestSongsUseCase @Inject constructor(
    private val songGateway: SongGateway
) {
    private val statusPublisher: PublishSubject<Status> = PublishSubject.create()
    var offset = 0

    fun execute(dropOffset: Boolean = false): Completable {
        if (dropOffset) {
            offset = 0
        }
        return songGateway.requestSongList(20, offset) //todo Константа
            .doOnError { statusPublisher.onNext(Status.Error(it)) }
            .doOnSubscribe { statusPublisher.onNext(Status.Loading) }
            .doOnComplete { statusPublisher.onNext(Status.Success) }
    }

    fun observeStatus(): Observable<Status> =
        statusPublisher.hide()

    sealed class Status {
        object Loading : Status()
        object Success : Status()
        data class Error(val throwable: Throwable) : Status()
    }
}