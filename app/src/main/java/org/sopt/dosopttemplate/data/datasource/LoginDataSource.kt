package org.sopt.dosopttemplate.data.datasource

import org.sopt.dosopttemplate.data.entity.request.RequestLoginDto
import org.sopt.dosopttemplate.data.entity.response.ResponseLoginDto
import org.sopt.dosopttemplate.data.entity.service.LoginService
import javax.inject.Inject

class LoginDataSource @Inject constructor(
    private val loginService: LoginService
) {
    suspend fun login(request: RequestLoginDto): ResponseLoginDto =
        loginService.login(request)
}
