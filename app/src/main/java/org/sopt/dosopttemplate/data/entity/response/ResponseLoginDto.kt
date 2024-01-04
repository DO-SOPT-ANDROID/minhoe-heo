package org.sopt.dosopttemplate.data.entity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseLoginDto(
    @SerialName("status")
    val status: Int,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: UserInfo,
) {
    @Serializable
    data class UserInfo(
        @SerialName("id")
        val id: Int,
        @SerialName("username")
        val username: String,
        @SerialName("nickname")
        val nickname: String
    )
}

