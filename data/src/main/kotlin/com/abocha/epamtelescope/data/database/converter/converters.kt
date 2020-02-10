package com.abocha.epamtelescope.data.database.converter

import androidx.room.TypeConverter
import com.abocha.epamtelescope.data.network.adapter.MoshiProvider
import com.abocha.epamtelescope.entities.ClampData
import com.abocha.epamtelescope.entities.Dictionary
import com.abocha.epamtelescope.entities.Photo
import com.abocha.epamtelescope.entities.SyncState
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime

class DictionariesConverter {
    @TypeConverter
    fun fromValue(value: List<Dictionary>?): String? =
        value?.let {
            MoshiProvider.listDictionaryAdapter.toJson(value)
        }

    @TypeConverter
    fun toValue(valueString: String?): List<Dictionary> =
        valueString?.let { MoshiProvider.listDictionaryAdapter.fromJson(it) } ?: emptyList()
}

class DictionaryConverter {
    @TypeConverter
    fun fromValue(value: Dictionary?): String? =
        value?.let {
            MoshiProvider.moshi.adapter(Dictionary::class.java).toJson(value)
        }

    @TypeConverter
    fun toValue(valueString: String?): Dictionary? =
        valueString?.let {
            MoshiProvider.moshi.adapter(Dictionary::class.java).fromJson(it)
        }
}

class ListClampDataConverter {
    @TypeConverter
    fun fromValue(value: List<ClampData>?): String? =
        value?.let {
            MoshiProvider.listClampDataAdapter.toJson(value)
        }

    @TypeConverter
    fun toValue(valueString: String?): List<ClampData> =
        valueString?.let { MoshiProvider.listClampDataAdapter.fromJson(it) } ?: emptyList()
}

class SyncStateConverter {
    @TypeConverter
    fun fromValue(value: SyncState): String =
        value.name

    @TypeConverter
    fun toValue(valueString: String): SyncState =
        SyncState.valueOf(valueString)
}

class ZonedDateConverter {
    @TypeConverter
    fun fromValue(value: ZonedDateTime): Long =
        value.toInstant().toEpochMilli()

    @TypeConverter
    fun toValue(value: Long): ZonedDateTime =
        Instant.ofEpochMilli(value).atZone(ZoneId.systemDefault())
}

class PhotosConverter {
    @TypeConverter
    fun fromValue(value: List<Photo>?): String? =
        value?.let {
            MoshiProvider.listPhotoAdapter.toJson(value)
        }

    @TypeConverter
    fun toValue(value: String?): List<Photo> =
        value?.let { MoshiProvider.listPhotoAdapter.fromJson(it) } ?: emptyList()
}
