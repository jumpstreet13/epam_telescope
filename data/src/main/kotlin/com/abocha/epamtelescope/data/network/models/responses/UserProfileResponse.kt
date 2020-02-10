package com.abocha.epamtelescope.data.network.models.responses

import com.abocha.epamtelescope.entities.Role
import com.abocha.epamtelescope.entities.User
import com.squareup.moshi.Json

data class UserProfileResponse(
    @field:Json(name = "id") val id: Long,
    @field:Json(name = "email") val email: String,
    @field:Json(name = "first_name") val firstName: String,
    @field:Json(name = "last_name") val lastName: String,
    @field:Json(name = "role") val role: Role,
    @field:Json(name = "is_archived") val isArchived: Boolean,
    @field:Json(name = "avatar") val avatarResponse: AvatarResponse?,
    @field:Json(name = "customer") val customer: String?
)

data class AvatarResponse(
    @field:Json(name = "small") val smallAvatar: String,
    @field:Json(name = "medium") val mediumAvatar: String,
    @field:Json(name = "original") val originalAvatar: String
)

fun UserProfileResponse.toDomainModel(): User =
    User(
        id = this.id,
        email = this.email,
        firstName = this.firstName,
        lastName = this.lastName,
        role = this.role,
        avatarUrl = this.avatarResponse?.mediumAvatar ?: ""
    )

