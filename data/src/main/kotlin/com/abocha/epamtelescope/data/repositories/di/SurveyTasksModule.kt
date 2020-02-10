package com.abocha.epamtelescope.data.repositories.di

import com.abocha.epamtelescope.entities.SurveyType
import com.abocha.epamtelescope.repository.*
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

/**
 * Created by Oleg Sheliakin on 2019-12-23.
 * Contact me by email - olegsheliakin@gmail.com
 */
@Suppress("ComplexInterface", "TooManyFunctions")
@Module
interface SurveyTasksModule {

    @Singleton
    @IntoMap
    @SurveyTypeKey(SurveyType.RISER_CLAMPS)
    @Binds
    fun bindRiserClampRepo(impl: RiserClampRepository): SurveyTasksRepository

    @Singleton
    @IntoMap
    @SurveyTypeKey(SurveyType.COMPONENT)
    @Binds
    fun bindComponentsRepo(impl: ComponentRepository): SurveyTasksRepository

    @Singleton
    @IntoMap
    @SurveyTypeKey(SurveyType.RISER)
    @Binds
    fun bindRisersRepo(impl: RiserRepository): SurveyTasksRepository

    @Singleton
    @IntoMap
    @SurveyTypeKey(SurveyType.CONDUCTOR_AND_WELLHEAD)
    @Binds
    fun bindConductorsRepo(impl: ConductorRepository): SurveyTasksRepository

    @Singleton
    @IntoMap
    @SurveyTypeKey(SurveyType.CATHODIC_PROTECTION)
    @Binds
    fun bindCathodicProtectionsRepo(impl: CathodicProtectionRepository):
            SurveyTasksRepository

    @Singleton
    @IntoMap
    @SurveyTypeKey(SurveyType.CATHODIC_PROTECTION_CALIBRATION)
    @Binds
    fun bindCathodicProtectionCalibrationsRepo(impl: CPCalibrationRepository):
            SurveyTasksRepository

    @Singleton
    @IntoMap
    @SurveyTypeKey(SurveyType.ISIMS)
    @Binds
    fun bindIsimsRepo(impl: IsimsRepository): SurveyTasksRepository

    @Singleton
    @Binds
    @IntoMap
    @SurveyTypeKey(SurveyType.OVERALL_PLATFORM_PHOTOS)
    fun bindPlatfromPhotosRepo(impl: PlatformPhotosRepository): SurveyTasksRepository

}
