package org.sopt.dosopttemplate.data.entity.service

import org.sopt.dosopttemplate.data.entity.request.RequestSignUpDto
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpService {
    @POST("api/v1/members")
    suspend fun signUp(
        @Body signUpReq: RequestSignUpDto,
    )
}
