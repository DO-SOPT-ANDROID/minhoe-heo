package org.sopt.dosopttemplate

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.ServicePool.authService
import org.sopt.dosopttemplate.request.RequestLoginDto
import org.sopt.dosopttemplate.request.RequestSignUpDto
import org.sopt.dosopttemplate.response.ResponseLoginDto

class AuthViewModel : ViewModel() {

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: MutableLiveData<Boolean>
        get() = _loginSuccess

    private var _loginResult = MutableLiveData<ResponseLoginDto>()
    val loginResult: MutableLiveData<ResponseLoginDto>
        get() = _loginResult

    private val _signUpSuccess = MutableLiveData<Boolean>()
    val signUpSuccess: MutableLiveData<Boolean>
        get() = _signUpSuccess

    val username = MutableLiveData("")
    val password = MutableLiveData("")
    val nickname = MutableLiveData("")
    val mbti = MutableLiveData("")

    val usernameLogin = MutableLiveData("")
    val passwordLogin = MutableLiveData("")

    val checkBtnEnabled = MediatorLiveData<Boolean>().apply {
        addSource(username) { value = isSignUpValid() }
        addSource(nickname) { value = isSignUpValid() }
        addSource(password) { value = isSignUpValid() }
        addSource(mbti) { value = isSignUpValid() }
        Log.d("signup", "싸인업")
    }

    private fun isSignUpValid(): Boolean {
        Log.e("signupnetwork", "싸인업")
        return isIdValid()
                && isPwValid()
                && !nickname.value.isNullOrBlank()
                && !mbti.value.isNullOrBlank()
    }

    fun isIdValid() = username.value?.matches(ID_REGEX.toRegex()) ?: false
    fun isPwValid() = password.value?.matches(PW_REGEX.toRegex()) ?: false

    fun login() {
        viewModelScope.launch {
            kotlin.runCatching {
                authService.login(
                    RequestLoginDto(
                        username.value.toString(),
                        password.value.toString()
                    )
                )
            }.onSuccess {
                if (it.isSuccessful) {
                    loginResult.value = it.body()
                    loginSuccess.value = true
                } else {
                    Log.d("login", "로그인 실패")
                    loginSuccess.value = false
                }
            }.onFailure {
                Log.e("LoginNetwork", "error:$it")
            }
        }
    }

    fun signUp() {
        viewModelScope.launch {
            kotlin.runCatching {
                authService.signUp(
                    RequestSignUpDto(
                        username.value.toString(),
                        password.value.toString(),
                        nickname.value.toString()
                    )
                )
            }.onSuccess {
                _signUpSuccess.value = true
            }.onFailure {
                _signUpSuccess.value = false
                Log.e("SignUpNetwork", "error:$it")
            }
        }
    }

    companion object {
        const val ID_REGEX = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{6,10}\$"
        const val PW_REGEX =
            "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[\$@\$!%*#?&.])[A-Za-z[0-9]\$@\$!%*#?&.]{6,12}\$"
    }
}

