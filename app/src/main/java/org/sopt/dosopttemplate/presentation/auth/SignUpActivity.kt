package org.sopt.dosopttemplate.presentation.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivitySignUpBinding
import org.sopt.dosopttemplate.util.extension.hideKeyboard

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private val authSignUpViewModel by viewModels<AuthViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.authSignUpViewModel = authSignUpViewModel

        checkIdValid()
        checkPwValid()
        signUp()

        binding.root.setOnClickListener {
            hideKeyboard(binding.root)
        }
    }

    private fun checkIdValid() {
        authSignUpViewModel.id.flowWithLifecycle(lifecycle).onEach { id ->
            if (!id.isNullOrBlank() && !authSignUpViewModel.isIdValid(id)) {
                binding.tvSignUpId.error = "영문, 숫자를 포함해 6-10자 이내로 쓰십시오"
            } else {
                binding.tvSignUpId.error = null
            }
        }.launchIn(lifecycleScope)

    }

    private fun checkPwValid() {
        authSignUpViewModel.password.flowWithLifecycle(lifecycle).onEach { password ->
            if (!password.isNullOrBlank() && !authSignUpViewModel.isPwValid(password)) {
                binding.tvSignUpPw.error = "영문,숫자,특수문자를 포함해 6-12자 이내로 쓰십시오"
            } else {
                binding.tvSignUpPw.error = null
            }
        }.launchIn(lifecycleScope)
    }

    private fun signUp() {
        authSignUpViewModel.isSignUpSuccessful.observe(this) {
            Log.e("signup", "${it}")
            if (it) {
                Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()
                startActivity(
                    Intent(
                        this,
                        LoginActivity::class.java,
                    )
                )
                Log.e("btn", "넘어가나?")
            } else {
                Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
