package org.sopt.dosopttemplate.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.data.entity.request.RequestSignUpDto
import org.sopt.dosopttemplate.data.entity.service.ServicePool

class SignUpViewModel : ViewModel() {

    private val _isSignUpSuccessful = MutableLiveData<Boolean>()
    val isSignUpSuccessful: LiveData<Boolean>
        get() = _isSignUpSuccessful

    val id = MutableStateFlow("")
    val password = MutableStateFlow("")
    val nickname = MutableStateFlow("")
    val mbti = MutableStateFlow("")

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

    fun isIdValid(id: String) = id.matches(ID_REGEX.toRegex())
    fun isPwValid(password: String) = password.matches(PW_REGEX.toRegex())

    fun signUp() {
        viewModelScope.launch {
            kotlin.runCatching {
                ServicePool.signUpService.signUp(
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
