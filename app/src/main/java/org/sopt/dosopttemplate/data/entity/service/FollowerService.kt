package org.sopt.dosopttemplate.data.entity.service

import org.sopt.dosopttemplate.data.entity.response.ResponseFollowerDto
import retrofit2.http.GET
import retrofit2.http.Query

interface FollowerService {
    @GET("api/users")
    suspend fun follower(
        @Query("page") num: Int = 2
    ): ResponseFollowerDto
}
