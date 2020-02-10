package com.abocha.epamtelescope.repository

import com.abocha.epamtelescope.entities.Assignment
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by Oleg Sheliakin on 2020-01-14.
 * Contact me by email - olegsheliakin@gmail.com
 */
interface AssignmentsRepository {

    fun streamAllAssignments(): Flowable<List<Assignment>>

    fun refresh(): Completable

}
