package org.sopt.dosopttemplate.presentation.auth

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.data.model.LoginState
import org.sopt.dosopttemplate.data.model.request.RequestLoginDto
import org.sopt.dosopttemplate.data.model.request.RequestSignUpDto
import org.sopt.dosopttemplate.data.model.service.ServicePool.authService

class AuthViewModel : ViewModel() {

    private val _isLoginState = MutableStateFlow<LoginState>(LoginState.Loading)
    val isLoginState: StateFlow<LoginState> = _isLoginState.asStateFlow()

    private val _isSignUpSuccessful = MutableLiveData<Boolean>()
    val isSignUpSuccessful: MutableLiveData<Boolean>
        get() = _isSignUpSuccessful

    val id = MutableStateFlow("")
    val password = MutableStateFlow("")
    val nickname = MutableStateFlow("")
    val mbti = MutableStateFlow("")

    val loginId = MutableStateFlow("")
    val loginPassword = MutableStateFlow("")

    val checkBtnEnabled: StateFlow<Boolean> = combine(
        id,
        password,
        nickname,
        mbti
    ) { idValue, passwordValue, nicknameValue, mbtiValue ->
        isSignUpValid(idValue, passwordValue, nicknameValue, mbtiValue)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), initialValue = false)

    private fun isSignUpValid(
        id: String,
        password: String,
        nickname: String,
        mbti: String
    ): Boolean {
        Log.e("signupnetwork", "싸인업")
        return isIdValid(id)
                && isPwValid(password)
                && !nickname.isNullOrBlank()
                && !mbti.isNullOrBlank()
    }

    fun isIdValid(id: String) = id.matches(ID_REGEX.toRegex()) ?: false
    fun isPwValid(password: String) = password.matches(PW_REGEX.toRegex()) ?: false

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

    fun signUp() {
        viewModelScope.launch {
            kotlin.runCatching {
                authService.signUp(
                    RequestSignUpDto(
                        id.value ?: "",
                        nickname.value ?: "",
                        password.value ?: ""
                    )
                )
            }.onSuccess {
                _isSignUpSuccessful.value = true
            }.onFailure {
                _isSignUpSuccessful.value = false
                Log.e("SignUpNetwork", "error:$it")
            }
        }
    }

    companion object {
        private const val ID_REGEX = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{6,10}\$"
        private const val PW_REGEX =
            "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[\$@\$!%*#?&.])[A-Za-z[0-9]\$@\$!%*#?&.]{6,12}\$"
    }
}

