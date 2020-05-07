package com.abocha.epamtelescope.data.network.adapter

import com.squareup.moshi.Moshi

object MoshiProvider {
    val moshi: Moshi by lazy {
        Moshi.Builder()
            .add(RoleMoshiAdapter())
            .add(ZonedDateTimeAdapter)
            .add(LocalDateAdapter)
            .build()
    }

/*    val listDictionaryAdapter: JsonAdapter<List<Dictionary>> by lazy {
        val type = Types.newParameterizedType(List::class.java, Dictionary::class.java)
        moshi.adapter<List<Dictionary>>(type)
    }

    val listClampDataAdapter: JsonAdapter<List<ClampData>> by lazy {
        val type = Types.newParameterizedType(List::class.java, ClampData::class.java)
        moshi.adapter<List<ClampData>>(type)
    }

    val listPhotoAdapter: JsonAdapter<List<Photo>> by lazy {
        val type = Types.newParameterizedType(List::class.java, Photo::class.java)
        moshi.adapter<List<Photo>>(type)
    }*/
}
