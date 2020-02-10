package com.abocha.epamtelescope.data.network.adapter

import com.abocha.epamtelescope.entities.Role
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class RoleMoshiAdapter {

    @ToJson
    fun toJson(roles: List<Role>): List<String> =
        roles.map { it.name }

    @FromJson
    fun fromJson(role: String): Role =
        when (role) {
            "admin" -> Role.ADMIN
            "engineer" -> Role.ENGINEER
            "inspector" -> Role.INSPECTOR
            "client" -> Role.CLIENT
            else -> Role.CLIENT
        }

}
