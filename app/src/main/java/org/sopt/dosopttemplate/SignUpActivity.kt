package org.sopt.dosopttemplate

import android.content.Context
import android.content.Intent
import android.os.Bundle
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

        canSignUp()
        observeSignUpResult()
        signUp()

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

    private fun canSignUp() {
        binding.signUp.setOnClickListener {
            val id = binding.etSignUpId.text.toString()
            val password = binding.etSignUpPw.text.toString()

//            val setId = id.matches("[a-zA-Z]+[0-9]+".toRegex())
//            val setPw = password.matches("(?=.*[a-zA-Z])(?=.*\\d).+".toRegex())

            if (binding.etSignUpId.length() in 6..10 && binding.etSignUpPw.length() in 6..12 && validateId(
                    id
                ) &&
                validatePassword(password) && !binding.etSignUpName.text.isNullOrBlank() && !binding.etSignUpMbti.text.isNullOrBlank()
            ) {
                intent = Intent(this, LoginActivity::class.java)
                    .putExtra("id", binding.etSignUpId.text.toString())
                    .putExtra("pw", binding.etSignUpPw.text.toString())
                    .putExtra("nickname", binding.etSignUpName.text.toString())
                    .putExtra("mbti", binding.etSignUpMbti.text.toString())

                setResult(RESULT_OK, intent)
                Toast.makeText(this, "회원가입에 성공했습니다.", Toast.LENGTH_SHORT).show()

                finish()
            } else
                Toast.makeText(this, "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    fun validateId(id: String): Boolean {
        val idPattern = "^[a-z0-9]{6,}\$".toRegex()
        return idPattern.matches(id)
    }

    fun validatePassword(password: String): Boolean {
        val passwordPattern =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+\$).{6,}\$".toRegex()
        return passwordPattern.matches(password)
    }

    private fun observeSignUpResult() {
        authSignUpViewModel.signUpSuccess.observe(this) {
            if (it) {
                Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()
                startActivity(
                    Intent(
                        this,
                        LoginActivity::class.java,
                    )
                )
            } else {
                Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun signUp() {
        binding.signUp.setOnClickListener {
            val id = binding.etSignUpId.text.toString()
            val password = binding.etSignUpPw.text.toString()
            val nickname = binding.etSignUpName.text.toString()
            val mbti = binding.etSignUpMbti.text.toString()

            authSignUpViewModel.signUp(
                id = id,
                password = password,
                nickname = nickname,
                mbti = mbti
            )

        }
    }
}