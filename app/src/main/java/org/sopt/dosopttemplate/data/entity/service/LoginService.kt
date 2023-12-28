package org.sopt.dosopttemplate.data.entity.service

import org.sopt.dosopttemplate.data.entity.request.RequestLoginDto
import org.sopt.dosopttemplate.data.entity.response.ResponseLoginDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("api/v1/members/sign-in")
    suspend fun login(
        @Body request: RequestLoginDto,
    ): Response<ResponseLoginDto>
}
