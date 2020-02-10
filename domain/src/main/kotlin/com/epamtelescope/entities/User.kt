package com.abocha.epamtelescope.entities

/**
 * Created by Oleg Sheliakin on 2019-12-23.
 * Contact me by email - olegsheliakin@gmail.com
 */
data class User(
    val id: Long,
    val email: String,
    val firstName: String,
    val lastName: String,
    val role: Role,
    val avatarUrl: String
)

fun User.getFullName(): String =
    "${this.firstName} ${this.lastName}"
