package org.sopt.dosopttemplate.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.data.entity.LoginState
import org.sopt.dosopttemplate.data.entity.response.ResponseLoginDto
import org.sopt.dosopttemplate.domain.repository.LoginRepository


class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _isLoginState = MutableStateFlow<LoginState>(LoginState.Loading)
    val isLoginState: StateFlow<LoginState> = _isLoginState.asStateFlow()

    val loginId = MutableStateFlow("")
    val loginPassword = MutableStateFlow("")


    fun login() {
        viewModelScope.launch {
            kotlin.runCatching {
                loginRepository.login(
                    loginId.value,
                    loginPassword.value
                )
            }.onSuccess { result ->
                val userData = result.getOrNull()
                userData?.let {
                    val userInfo =
                        ResponseLoginDto.UserInfo(it.id, it.username, it.nickname)
                    _isLoginState.value = LoginState.Success(userInfo)
                } ?: run {
                    _isLoginState.value = LoginState.Error
                }
            }.onFailure {
                _isLoginState.value = LoginState.Error
            }
        }
    }
}

