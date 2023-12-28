package org.sopt.dosopttemplate.data.datasource

import org.sopt.dosopttemplate.data.entity.request.RequestSignUpDto
import org.sopt.dosopttemplate.data.entity.service.ServicePool

class SignUpDataSource {
    private val signUpService = ServicePool.signUpService
    suspend fun signUp(request: RequestSignUpDto) = signUpService.signUp(request)
}
