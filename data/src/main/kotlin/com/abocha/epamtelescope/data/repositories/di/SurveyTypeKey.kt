package com.abocha.epamtelescope.data.repositories.di

import com.abocha.epamtelescope.entities.SurveyType
import dagger.MapKey

/**
 * Created by Oleg Sheliakin on 2020-01-29.
 * Contact me by email - olegsheliakin@gmail.com
 */
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@kotlin.annotation.Retention
@MapKey
annotation class SurveyTypeKey(val value: SurveyType)
