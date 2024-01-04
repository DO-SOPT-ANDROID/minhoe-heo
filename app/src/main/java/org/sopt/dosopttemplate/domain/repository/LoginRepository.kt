package org.sopt.dosopttemplate.domain.repository

import org.sopt.dosopttemplate.data.entity.response.ResponseLoginDto

interface LoginRepository {
    suspend fun login(id: String, password: String): Result<ResponseLoginDto.UserInfo>
}
