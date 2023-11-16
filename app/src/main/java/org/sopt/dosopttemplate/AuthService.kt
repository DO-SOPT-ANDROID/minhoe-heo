package org.sopt.dosopttemplate

import org.sopt.dosopttemplate.request.RequestLoginDto
import org.sopt.dosopttemplate.request.RequestSignUpDto
import org.sopt.dosopttemplate.response.ResponseLoginDto
import org.sopt.dosopttemplate.response.ResponseSignUpDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/v1/members")
    fun signUp(
        @Body signUpReq: RequestSignUpDto,
    ): Call<ResponseSignUpDto>

    @POST("api/v1/members/sign-in")
    fun login(
        @Body request: RequestLoginDto,
    ): Call<ResponseLoginDto>
}