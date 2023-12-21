package org.sopt.dosopttemplate.presentation.auth

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.data.model.LoginState
import org.sopt.dosopttemplate.data.model.request.RequestLoginDto
import org.sopt.dosopttemplate.data.model.request.RequestSignUpDto
import org.sopt.dosopttemplate.data.model.service.ServicePool.authService

class AuthViewModel : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Loading)
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()


    private val _isSignUpSuccessful = MutableLiveData<Boolean>()
    val isSignUpSuccessful: MutableLiveData<Boolean>
        get() = _isSignUpSuccessful

    val id = MutableLiveData("")
    val password = MutableLiveData("")
    val nickname = MutableLiveData("")
    val mbti = MutableLiveData("")

    private val _loginId = MutableStateFlow("")
    val loginId: StateFlow<String> = _loginId

    private val _loginPassword = MutableStateFlow("")
    val loginPassword: StateFlow<String> = _loginPassword

    val checkBtnEnabled = MediatorLiveData<Boolean>().apply {
        listOf(id, nickname, password, mbti).forEach { source ->
            addSource(source) { value = isSignUpValid() }
        }
    }

    private fun isSignUpValid(): Boolean {
        Log.e("signupnetwork", "싸인업")
        return isIdValid()
                && isPwValid()
                && !nickname.value.isNullOrBlank()
                && !mbti.value.isNullOrBlank()
    }

    fun isIdValid() = id.value?.matches(ID_REGEX.toRegex()) ?: false
    fun isPwValid() = password.value?.matches(PW_REGEX.toRegex()) ?: false


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
                            _loginState.value = LoginState.Success(body)
                        } else {
                            _loginState.value = LoginState.Error
                        }
                    }

                    400 -> {
                        _loginState.value = LoginState.Error
                    }
                }
            }.onFailure {
                _loginState.value = LoginState.Error
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

