package org.sopt.dosopttemplate.presentation.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.model.LoginState
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.presentation.home.HomeActivity
import org.sopt.dosopttemplate.util.extension.hideKeyboard


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val authViewModel by viewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.authViewModel = authViewModel

        observeLoginResult()
        addClickListener()
    }

    fun addClickListener() {
        binding.signUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.root.setOnClickListener {
            hideKeyboard(binding.root)
        }
    }

    private fun observeLoginResult() {
        lifecycleScope.launch {
            authViewModel.isLoginState.flowWithLifecycle(lifecycle).onEach { state ->
                when (state) {
                    is LoginState.Success -> {
                        Toast.makeText(this@LoginActivity, "로그인 성공", Toast.LENGTH_SHORT).show()
                        startActivity(
                            Intent(
                                this@LoginActivity,
                                HomeActivity::class.java,
                            ),
                        )
                    }

                    LoginState.Error -> {
                        Toast.makeText(this@LoginActivity, "로그인 실패", Toast.LENGTH_SHORT).show()
                    }

                    else -> {}
                }
            }
        }
    }
}

