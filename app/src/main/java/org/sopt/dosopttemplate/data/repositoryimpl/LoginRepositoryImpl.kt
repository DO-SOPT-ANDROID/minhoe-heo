package org.sopt.dosopttemplate.data.repositoryimpl

import org.sopt.dosopttemplate.data.datasource.LoginDataSource
import org.sopt.dosopttemplate.data.entity.request.RequestLoginDto
import org.sopt.dosopttemplate.data.entity.response.ResponseLoginDto
import org.sopt.dosopttemplate.domain.repository.LoginRepository

class LoginRepositoryImpl(private val loginDataSource: LoginDataSource) :
    LoginRepository {
    override suspend fun login(
        username: String,
        password: String
    ): Result<ResponseLoginDto.UserInfo> =
        runCatching {
            loginDataSource.login(RequestLoginDto(username, password)).data
        }
}
