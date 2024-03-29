package org.sopt.dosopttemplate.data.entity

import org.sopt.dosopttemplate.data.entity.response.ResponseLoginDto

sealed class LoginState {
    object Loading : LoginState()
    data class Success(val data: ResponseLoginDto.UserInfo) : LoginState()
    object Error : LoginState()
}
