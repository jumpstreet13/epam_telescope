package com.abocha.epamtelescope.entities

import java.util.*

data class Dictionaries(
    val map: EnumMap<DictionaryType, List<Dictionary>>
)

fun Dictionaries.find(dictionaryType: DictionaryType) =
    map[dictionaryType].orEmpty()

