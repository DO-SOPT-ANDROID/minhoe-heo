package org.sopt.dosopttemplate.domain.repository

interface SignUpRepository {
    suspend fun signUp(id: String, password: String, nickname: String): Result<Unit>
}
