package com.abocha.epamtelescope.data.repositories.di

import com.abocha.epamtelescope.data.network.cleaner.DataCleaner
import com.abocha.epamtelescope.data.network.cleaner.DataCleanerImpl
import com.abocha.epamtelescope.data.repositories.AssignmentsRepositoryImpl
import com.abocha.epamtelescope.data.repositories.AuthRepositoryImpl
import com.abocha.epamtelescope.data.repositories.CPCalibrationsRepositoryImpl
import com.abocha.epamtelescope.data.repositories.CathodicProtectionsRepositoryImpl
import com.abocha.epamtelescope.data.repositories.ChangePasswordRepositoryImpl
import com.abocha.epamtelescope.data.repositories.ComponentsRepositoryImpl
import com.abocha.epamtelescope.data.repositories.ConductorsRepositoryImpl
import com.abocha.epamtelescope.data.repositories.DictionariesRepositoryImpl
import com.abocha.epamtelescope.data.repositories.IsimsRepositoryImpl
import com.abocha.epamtelescope.data.repositories.PlatformsRepositoryImpl
import com.abocha.epamtelescope.data.repositories.PlatfromPhotosRepositoryImpl
import com.abocha.epamtelescope.data.repositories.ProfileRepositoryImpl
import com.abocha.epamtelescope.data.repositories.RecoveryPasswordRepositoryImpl
import com.abocha.epamtelescope.data.repositories.RiserClampsRepositoryImpl
import com.abocha.epamtelescope.data.repositories.RisersRepositoryImpl
import com.abocha.epamtelescope.data.repositories.SongRepository
import com.abocha.epamtelescope.data.repositories.SurveyRepositoryImpl
import com.abocha.epamtelescope.data.repositories.WellSlotsRepositoryImpl
import com.abocha.epamtelescope.data.repositories.WorkRepositoryImpl
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
import com.abocha.epamtelescope.repository.WellSlotsRepository
import com.abocha.epamtelescope.repository.WorkRepository
import com.epamtelescope.repository.SongGateway
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * Created by Oleg Sheliakin on 2019-12-23.
 * Contact me by email - olegsheliakin@gmail.com
 */
@Suppress("ComplexInterface", "TooManyFunctions")
@Module
interface GatewayModule {

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
    fun bindSongRepo(impl: SongRepository): SongGateway

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
