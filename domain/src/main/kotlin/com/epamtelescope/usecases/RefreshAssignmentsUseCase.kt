package com.abocha.epamtelescope.usecases

import com.abocha.epamtelescope.repository.AssignmentsRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Created by Oleg Sheliakin on 2020-01-14.
 * Contact me by email - olegsheliakin@gmail.com
 */
class RefreshAssignmentsUseCase constructor(
    private val assignmentsRepository: AssignmentsRepository
) {

    private val statusPublisher: PublishSubject<Status> = PublishSubject.create()

    fun execute(): Completable =
        assignmentsRepository.refresh()
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
