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

    private val _isLoginSuccessful = MutableLiveData<Boolean>()
    val loginSuccess: MutableLiveData<Boolean>
        get() = _isLoginSuccessful

    private val _loginResult = MutableLiveData<ResponseLoginDto>()
    val loginResult: MutableLiveData<ResponseLoginDto>
        get() = _loginResult

    private val _signUpSuccess = MutableLiveData<Boolean>()
    val signUpSuccess: MutableLiveData<Boolean>
        get() = _signUpSuccess

    val id = MutableLiveData("")
    val password = MutableLiveData("")
    val nickname = MutableLiveData("")
    val mbti = MutableLiveData("")

    val loginId = MutableLiveData("")
    val loginPassword = MutableLiveData("")

    val checkBtnEnabled = MediatorLiveData<Boolean>().apply {
        addSource(id) { value = isSignUpValid() }
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
                Log.e("Login response", "${it.body()},${it.code()}")
                if (it.code() == 200) {
                    loginResult.value = it.body()
                    _isLoginSuccessful.value = true
                }
                if (it.code() == 400) {
                    Log.d("login", "${it}")
                    _isLoginSuccessful.value = false
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
                        id.value ?: "",
                        password.value ?: "",
                        nickname.value ?: ""
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
        private const val ID_REGEX = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{6,10}\$"
        private const val PW_REGEX =
            "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[\$@\$!%*#?&.])[A-Za-z[0-9]\$@\$!%*#?&.]{6,12}\$"
    }
}

