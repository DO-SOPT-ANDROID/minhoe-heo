package org.sopt.dosopttemplate.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.model.LoginState
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.ui.home.HomeActivity
import org.sopt.dosopttemplate.util.extension.hideKeyboard
import org.sopt.dosopttemplate.util.extension.toast


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.loginViewModel = loginViewModel

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
            loginViewModel.isLoginState.flowWithLifecycle(lifecycle).onEach { state ->
                when (state) {
                    is LoginState.Success -> {
                        toast(getString(R.string.login_success))
                        startActivity(
                            Intent(
                                this@LoginActivity,
                                HomeActivity::class.java,
                            ),
                        )
                    }

                    LoginState.Error -> {
                        toast(getString(R.string.login_fail))
                    }

                    else -> {}
                }
            }.launchIn(lifecycleScope)
        }
    }
}
