package org.sopt.dosopttemplate

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.extension.hideKeyboard


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val authViewModel by viewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        observeLoginResult()

        binding.root.setOnClickListener {
            hideKeyboard(binding.root)
        }

        binding.lifecycleOwner = this
        binding.authViewModel = authViewModel

    }


    private fun observeLoginResult() {
        authViewModel.isLoginSuccessful.observe(this) {
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
}
