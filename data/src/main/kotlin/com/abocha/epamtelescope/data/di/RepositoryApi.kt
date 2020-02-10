package com.abocha.epamtelescope.data.di

import com.abocha.epamtelescope.repository.*

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
