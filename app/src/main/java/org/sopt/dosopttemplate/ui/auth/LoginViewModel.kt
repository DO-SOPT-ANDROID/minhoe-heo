package org.sopt.dosopttemplate.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.data.model.LoginState
import org.sopt.dosopttemplate.data.model.request.RequestLoginDto
import org.sopt.dosopttemplate.data.model.service.ServicePool.authService

class LoginViewModel : ViewModel() {

    private val _isLoginState = MutableStateFlow<LoginState>(LoginState.Loading)
    val isLoginState: StateFlow<LoginState> = _isLoginState.asStateFlow()

    val loginId = MutableStateFlow("")
    val loginPassword = MutableStateFlow("")

    fun login() {
        viewModelScope.launch {
            kotlin.runCatching {
                authService.login(
                    RequestLoginDto(
                        loginId.value ?: "",
                        loginPassword.value ?: ""
                    )
                )
            }.onSuccess {
                when (it.code()) {
                    200 -> {
                        val body = it.body()
                        if (body != null) {
                            _isLoginState.value = LoginState.Success(body)
                        } else {
                            _isLoginState.value = LoginState.Error
                        }
                    }

                    400 -> {
                        _isLoginState.value = LoginState.Error
                    }
                }
            }.onFailure {
                _isLoginState.value = LoginState.Error
            }
        }
    }
}

