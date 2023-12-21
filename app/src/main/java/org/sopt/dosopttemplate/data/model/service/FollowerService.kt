package org.sopt.dosopttemplate.data.model.service

import org.sopt.dosopttemplate.data.model.response.ResponseFollowerDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FollowerService {
    @GET("api/users")
    fun follower(
        @Query("page") num: Int = 2
    ): Call<ResponseFollowerDto>
}

/*interface FollowerService {
    @GET("/api/users?page=2")
    fun follower(): Call<ResponseFollowerDto>
}*/
