package com.abocha.epamtelescope.usecases

import com.abocha.epamtelescope.entities.Assignment
import com.abocha.epamtelescope.repository.AssignmentsRepository
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by Oleg Sheliakin on 2020-01-14.
 * Contact me by email - olegsheliakin@gmail.com
 */
class GetAllAssignmentsUseCase @Inject constructor(private val assignmentsRepository: AssignmentsRepository) {

    fun execute(): Flowable<List<Assignment>> =
        assignmentsRepository.streamAllAssignments()

}
