package com.ouvrirdeveloper.data.models.responses

import com.ouvrirdeveloper.core.model.Mapper
import com.ouvrirdeveloper.domain.models.User
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponse(
    @Json(name = "UserInfo")
    val userInfo: List<UserInfo>
) : Mapper<User> {
    override fun toDomainModel(): User? {
        return userInfo.firstOrNull()?.run {
            User(
                pin = pin
            )
        }
    }
}

@JsonClass(generateAdapter = true)
data class UserInfo(
    @Json(name = "Usr_ID_N")
    val pin: Int
)
