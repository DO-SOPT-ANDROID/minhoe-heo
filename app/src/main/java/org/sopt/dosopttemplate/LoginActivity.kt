package org.sopt.dosopttemplate

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val authViewModel by viewModels<AuthViewModel>()
    private lateinit var activityResult: ActivityResultLauncher<Intent>
    lateinit var id: String
    lateinit var pw: String
    lateinit var nickname: String
    lateinit var mbti: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setResult()

        binding.signUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            activityResult.launch(intent)
        }

        observeLoginResult()
        login()

        binding.root.setOnClickListener {
            hideKeyboard()
        }

        binding.lifecycleOwner = this
        binding.authViewModel = authViewModel

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

    private fun canLogin() {
        if (binding.etSignInId.text.toString() == id && binding.etSignInPw.text.toString() == pw) {
            val intent = Intent(this, HomeActivity::class.java).apply {
                putExtra("id", id)
                putExtra("mbti", mbti)
                putExtra("nickname", nickname)
                putExtra("pw", pw)
            }

            startActivity(intent)
            finish()

        } else {
            Toast.makeText(this, "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setResult() {
        activityResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                id = result.data?.getStringExtra("id") ?: ""
                pw = result.data?.getStringExtra("pw") ?: ""
                nickname = result.data?.getStringExtra("nickname") ?: ""
                mbti = result.data?.getStringExtra("mbti") ?: ""

                canLogin()
            }
        }
    }

    private fun observeLoginResult() {
        authViewModel.loginSuccess.observe(this) {
            if (it) {
                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                startActivity(
                    Intent(
                        this,
                        HomeActivity::class.java,
                    ),
                )
            } else {
                Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun login() {
        binding.signIn.setOnClickListener {
            val id = binding.etSignInId.text.toString()
            val password = binding.etSignInPw.text.toString()

            authViewModel.login(
                id = id,
                password = password
            )
        }
    }
}