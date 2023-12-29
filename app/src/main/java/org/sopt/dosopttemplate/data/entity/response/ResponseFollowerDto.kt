package org.sopt.dosopttemplate.data.entity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.dosopttemplate.domain.model.Follower

@Serializable
data class ResponseFollowerDto(
    @SerialName("page")
    val page: Int,
    @SerialName("per_page")
    val perPage: Int,
    @SerialName("total")
    val total: Int,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("data")
    val data: List<FollowerData>,
    @SerialName("support")
    val support: Support
) {
    @Serializable
    data class FollowerData(
        @SerialName("id")
        val id: Int,
        @SerialName("email")
        val email: String,
        @SerialName("first_name")
        val firstName: String,
        @SerialName("last_name")
        val lastName: String,
        @SerialName("avatar")
        val avatar: String
    )

    @Serializable
    data class Support(
        @SerialName("url")
        val url: String,
        @SerialName("text")
        val text: String
    )

    fun toFollower() = data.map { data ->
        Follower(
            id = data.id,
            avatar = data.avatar,
            email = data.email,
            firstName = data.firstName
        )
    }
}

