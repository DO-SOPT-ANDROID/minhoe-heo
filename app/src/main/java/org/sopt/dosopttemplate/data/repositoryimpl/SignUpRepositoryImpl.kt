package org.sopt.dosopttemplate.data.repositoryimpl

import org.sopt.dosopttemplate.data.datasource.SignUpDataSource
import org.sopt.dosopttemplate.data.entity.request.RequestSignUpDto
import org.sopt.dosopttemplate.domain.repository.SignUpRepository
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(private val signUpDataSource: SignUpDataSource) :
    SignUpRepository {
    override suspend fun signUp(id: String, password: String, nickname: String): Result<Unit> =
        runCatching {
            signUpDataSource.signUp(RequestSignUpDto(id, password, nickname))
        }
}
