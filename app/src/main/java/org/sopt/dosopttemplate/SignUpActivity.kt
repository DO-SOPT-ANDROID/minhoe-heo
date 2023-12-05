package org.sopt.dosopttemplate

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.databinding.SignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: SignUpBinding
    private val authSignUpViewModel by viewModels<AuthViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up)
        binding = SignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signUp()
        checkIdValid()
        checkPwValid()

        binding.root.setOnClickListener {
            hideKeyboard()
        }

        binding.lifecycleOwner = this
        binding.authSignUpViewModel = authSignUpViewModel

    }

    private fun hideKeyboard() {
        if (this != null && this.currentFocus != null) {
            val inputManager: InputMethodManager = this.getSystemService(
                Context.INPUT_METHOD_SERVICE
            ) as InputMethodManager
            inputManager.hideSoftInputFromWindow(
                this.currentFocus?.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }

    private fun checkIdValid() {
        authSignUpViewModel.username.observe(this) { id ->
            Log.e("id", "${id}")
            if (id.isNullOrBlank() || authSignUpViewModel.isIdValid()) {
                binding.tvSignUpId.error = null
            } else if (!authSignUpViewModel.isIdValid()) {
                binding.tvSignUpId.error = "영문, 숫자를 포함해 6-10자 이내로 쓰십시오"
            }
        }
    }

    private fun checkPwValid() {
        authSignUpViewModel.password.observe(this) { password ->
            Log.e("password", "${password}")
            if (password.isNullOrBlank() || authSignUpViewModel.isPwValid()) {
                binding.tvSignUpPw.error = null
            } else if (!authSignUpViewModel.isPwValid()) {
                binding.tvSignUpPw.error = "영문,숫자,특수문자를 포함해 6-12자 이내로 쓰십시오"
            }
        }
    }

    private fun signUp() {
        binding.signUp.setOnClickListener {
            val id = binding.etSignUpId.text.toString()
            val password = binding.etSignUpPw.text.toString()
            val nickname = binding.etSignUpName.text.toString()

            Log.e("사용자 정보", "${id}, ${password}, ${nickname}")

            authSignUpViewModel.signUp(
                username = id,
                password = password,
                nickname = nickname
            )

            authSignUpViewModel.signUpSuccess.observe(this) {
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
}


