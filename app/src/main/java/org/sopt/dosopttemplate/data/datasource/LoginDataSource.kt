package org.sopt.dosopttemplate.data.datasource

import org.sopt.dosopttemplate.data.entity.request.RequestLoginDto
import org.sopt.dosopttemplate.data.entity.response.ResponseLoginDto
import org.sopt.dosopttemplate.data.entity.service.ServicePool
import retrofit2.Response

class LoginDataSource {
    private val loginService = ServicePool.loginService
    suspend fun login(request: RequestLoginDto): Response<ResponseLoginDto> =
        loginService.login(request)
}
