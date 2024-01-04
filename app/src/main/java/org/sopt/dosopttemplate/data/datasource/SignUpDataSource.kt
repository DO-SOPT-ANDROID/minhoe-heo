package org.sopt.dosopttemplate.data.datasource

import org.sopt.dosopttemplate.data.entity.request.RequestSignUpDto
import org.sopt.dosopttemplate.data.entity.service.SignUpService
import javax.inject.Inject

class SignUpDataSource @Inject constructor(
    private val signUpService: SignUpService
) {
    suspend fun signUp(request: RequestSignUpDto) = signUpService.signUp(request)
}
