package com.abocha.epamtelescope.data.network.models.responses

import com.abocha.epamtelescope.entities.Dictionary
import com.abocha.epamtelescope.entities.DictionaryType
import com.squareup.moshi.Json

data class DictionariesResponse(
    @field:Json(name = "ClampType") val clampType: List<DictionaryResponse>?,
    @field:Json(name = "CoatingConditionType") val coatingConditionTypeResponse: List<DictionaryResponse>?,
    @field:Json(name = "ComponentType") val componentTypeResponse: List<DictionaryResponse>?,
    @field:Json(name = "ConductorAreaType") val conductorAreaTypeResponse: List<DictionaryResponse>?,
    @field:Json(name = "MechanicalDamageType") val mechanicalDamageTypeResponse: List<DictionaryResponse>?,
    @field:Json(name = "PlatformAreaType") val platformAreaTypeResponse: List<DictionaryResponse>?,
    @field:Json(name = "PlatformPhotoType") val platformPhotoTypeResponse: List<DictionaryResponse>?,
    @field:Json(name = "RiserType") val riserTypeResponse: List<DictionaryResponse>?,
    @field:Json(name = "SideType") val sideTypeResponse: List<DictionaryResponse>?
)

fun DictionaryResponse.toDomainModel(dictionaryType: DictionaryType): Dictionary =
    Dictionary(
        id = this.id,
        title = this.title,
        dictionaryType = dictionaryType
    )
