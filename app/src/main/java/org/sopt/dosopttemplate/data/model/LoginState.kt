package org.sopt.dosopttemplate.data.model

import org.sopt.dosopttemplate.data.model.response.ResponseLoginDto

sealed class LoginState {
    object Loading : LoginState()
    data class Success(val data: ResponseLoginDto) : LoginState()
    object Error : LoginState()
}
