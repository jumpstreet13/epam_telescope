package com.abocha.epamtelescope.data.repositories.di

import com.abocha.epamtelescope.data.network.cleaner.DataCleaner
import com.abocha.epamtelescope.data.network.cleaner.DataCleanerImpl
import com.abocha.epamtelescope.data.repositories.ProfileRepositoryImpl
import com.abocha.epamtelescope.data.repositories.*
import com.abocha.epamtelescope.repository.*
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * Created by Oleg Sheliakin on 2019-12-23.
 * Contact me by email - olegsheliakin@gmail.com
 */
@Suppress("ComplexInterface", "TooManyFunctions")
@Module
interface RepositoryModule {

    @Singleton
    @Binds
    fun provideDataCleaner(cleaner: DataCleanerImpl): DataCleaner

    @Binds
    fun bindAuthRepo(impl: AuthRepositoryImpl): AuthRepository

    @Singleton
    @Binds
    fun bindRecoveryPasswordRepo(impl: RecoveryPasswordRepositoryImpl): RecoveryPasswordRepository

    @Singleton
    @Binds
    fun bindChangePasswordRepo(impl: ChangePasswordRepositoryImpl): ChangePasswordRepository

    @Singleton
    @Binds
    fun bindAssignmentRepo(impl: AssignmentsRepositoryImpl): AssignmentsRepository

    @Singleton
    @Binds
    fun bindPlatfromsRepo(impl: PlatformsRepositoryImpl): PlatformsRepository

    @Singleton
    @Binds
    fun bindProfileRepo(impl: ProfileRepositoryImpl): ProfileRepository

    @Singleton
    @Binds
    fun bindDictionariesRepo(impl: DictionariesRepositoryImpl): DictionariesRepository

    @Singleton
    @Binds
    fun bindWorkRepo(impl: WorkRepositoryImpl): WorkRepository

    @Singleton
    @Binds
    fun bindRiserClampRepo(impl: RiserClampsRepositoryImpl): RiserClampRepository

    @Singleton
    @Binds
    fun bindComponentsRepo(impl: ComponentsRepositoryImpl): ComponentRepository

    @Singleton
    @Binds
    fun bindRisersRepo(impl: RisersRepositoryImpl): RiserRepository

    @Singleton
    @Binds
    fun bindConductorsRepo(impl: ConductorsRepositoryImpl): ConductorRepository

    @Singleton
    @Binds
    fun bindCathodicProtectionsRepo(impl: CathodicProtectionsRepositoryImpl):
            CathodicProtectionRepository

    @Singleton
    @Binds
    fun bindCathodicProtectionCalibrationsRepo(impl: CPCalibrationsRepositoryImpl):
            CPCalibrationRepository

    @Singleton
    @Binds
    fun bindIsimsRepo(impl: IsimsRepositoryImpl): IsimsRepository

    @Singleton
    @Binds
    fun bindPlatfromPhotosRepo(impl: PlatfromPhotosRepositoryImpl): PlatformPhotosRepository

    @Singleton
    @Binds
    fun bindSurveyRepo(impl: SurveyRepositoryImpl): SurveyRepository

    @Singleton
    @Binds
    fun bindWellSlotsRepo(impl: WellSlotsRepositoryImpl): WellSlotsRepository

}
