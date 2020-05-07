package com.abocha.epamtelescope.data.di

import com.abocha.epamtelescope.repository.AssignmentsRepository
import com.abocha.epamtelescope.repository.AuthRepository
import com.abocha.epamtelescope.repository.CPCalibrationRepository
import com.abocha.epamtelescope.repository.CathodicProtectionRepository
import com.abocha.epamtelescope.repository.ChangePasswordRepository
import com.abocha.epamtelescope.repository.ComponentRepository
import com.abocha.epamtelescope.repository.ConductorRepository
import com.abocha.epamtelescope.repository.DictionariesRepository
import com.abocha.epamtelescope.repository.IsimsRepository
import com.abocha.epamtelescope.repository.PlatformPhotosRepository
import com.abocha.epamtelescope.repository.PlatformsRepository
import com.abocha.epamtelescope.repository.ProfileRepository
import com.abocha.epamtelescope.repository.RecoveryPasswordRepository
import com.abocha.epamtelescope.repository.RiserClampRepository
import com.abocha.epamtelescope.repository.RiserRepository
import com.abocha.epamtelescope.repository.SurveyRepository
import com.abocha.epamtelescope.repository.SurveyTasksRepositoriesFactory
import com.abocha.epamtelescope.repository.WellSlotsRepository
import com.abocha.epamtelescope.repository.WorkRepository
import com.epamtelescope.repository.SongGateway

/**
 * Created by Oleg Sheliakin on 2019-12-23.
 * Contact me by email - olegsheliakin@gmail.com
 */
@Suppress("ComplexInterface", "TooManyFunctions")
interface RepositoryApi {
    fun authRepo(): AuthRepository
    fun recoveryPasswordRepo(): RecoveryPasswordRepository
    fun changePasswordRepo(): ChangePasswordRepository
    fun assignmentRepository(): AssignmentsRepository
    fun songRepository(): SongGateway
    fun platformRepository(): PlatformsRepository
    fun dictionariesRepo(): DictionariesRepository
    fun profileRepo(): ProfileRepository
    fun workRepo(): WorkRepository
    fun surveyRepo(): SurveyRepository
    fun cathodicProtectionCalibrationRepo(): CPCalibrationRepository
    fun cathodicProtectionRepo(): CathodicProtectionRepository
    fun componentRepo(): ComponentRepository
    fun conductorRepo(): ConductorRepository
    fun isimsRepo(): IsimsRepository
    fun riserRepo(): RiserRepository
    fun riserClampsRepo(): RiserClampRepository
    fun platformPhotosRepo(): PlatformPhotosRepository
    fun surveyTasksFactory(): SurveyTasksRepositoriesFactory
    fun wellSlotsRepository(): WellSlotsRepository
}
