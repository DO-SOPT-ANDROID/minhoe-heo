package org.sopt.dosopttemplate

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.ServicePool.authService
import org.sopt.dosopttemplate.request.RequestLoginDto
import org.sopt.dosopttemplate.response.ResponseLoginDto

class AuthViewModel : ViewModel() {

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: MutableLiveData<Boolean>
        get() = _loginSuccess

    private var _loginResult = MutableLiveData<ResponseLoginDto>()
    val loginResult: MutableLiveData<ResponseLoginDto>
        get() = _loginResult

    val isLoginButtonClicked: MutableLiveData<Boolean> = MutableLiveData(false)

    fun onLoginButtonClick() {
        isLoginButtonClicked.value = true
    }

    fun login(id: String, password: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                authService.login(RequestLoginDto(id, password))
            }.onSuccess {
                if (it.isSuccessful) {
                    loginResult.value = it.body()
                    loginSuccess.value = true
                } else {
                    loginSuccess.value = false
                }
            }.onFailure {
                Log.e("LoginNetwork", "error:$it")
            }
        }
    }
}
