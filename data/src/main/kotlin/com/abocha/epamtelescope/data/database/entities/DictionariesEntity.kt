package com.abocha.epamtelescope.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.abocha.epamtelescope.entities.Dictionaries
import com.abocha.epamtelescope.entities.Dictionary
import com.abocha.epamtelescope.entities.DictionaryType
import java.util.*

@Entity(tableName = DictionariesEntity.TABLE_NAME)
data class DictionariesEntity(
    @PrimaryKey
    val id: Long,
    val clampTypeEntity: List<Dictionary>,
    val coatingConditionType: List<Dictionary>,
    val componentType: List<Dictionary>,
    val conductorAreaType: List<Dictionary>,
    val mechanicalDamageType: List<Dictionary>,
    val platformAreaType: List<Dictionary>,
    val platformPhotoType: List<Dictionary>,
    val riserType: List<Dictionary>,
    val sideType: List<Dictionary>
) {
    companion object {
        const val TABLE_NAME = "dictionaries_entity"
    }
}

fun DictionariesEntity.toDomainModel() = Dictionaries(
    map = createEnumMap(this)
)

private fun createEnumMap(dictionariesEntity: DictionariesEntity): EnumMap<DictionaryType, List<Dictionary>> =
    EnumMap<DictionaryType, List<Dictionary>>(DictionaryType::class.java).apply {
        put(DictionaryType.CLAMP, dictionariesEntity.clampTypeEntity)
        put(DictionaryType.COATING_CONDITION, dictionariesEntity.coatingConditionType)
        put(DictionaryType.COMPONENT, dictionariesEntity.componentType)
        put(DictionaryType.CONDUCTOR_AREA, dictionariesEntity.conductorAreaType)
        put(DictionaryType.MECHANICAL_DAMAGE, dictionariesEntity.mechanicalDamageType)
        put(DictionaryType.PLATFORM_AREA, dictionariesEntity.platformAreaType)
        put(DictionaryType.PLATFORM_PHOTO, dictionariesEntity.platformPhotoType)
        put(DictionaryType.RISER, dictionariesEntity.riserType)
        put(DictionaryType.SIDE, dictionariesEntity.sideType)
    }
