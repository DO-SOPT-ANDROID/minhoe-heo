package org.sopt.dosopttemplate.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivitySignUpBinding
import org.sopt.dosopttemplate.util.extension.hideKeyboard
import org.sopt.dosopttemplate.util.extension.toast

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private val signUpViewModel by viewModels<SignUpViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.signUpViewModel = signUpViewModel

        checkIdValid()
        checkPwValid()
        signUp()

        binding.root.setOnClickListener {
            hideKeyboard(binding.root)
        }
    }

    private fun checkIdValid() {
        signUpViewModel.id.flowWithLifecycle(lifecycle).onEach { id ->
            if (!id.isNullOrBlank() && !signUpViewModel.isIdValid(id)) {
                binding.tvSignUpId.error = getString(R.string.check_id_valid)
            } else {
                binding.tvSignUpId.error = null
            }
        }.launchIn(lifecycleScope)

    }

    private fun checkPwValid() {
        signUpViewModel.password.flowWithLifecycle(lifecycle).onEach { password ->
            if (!password.isNullOrBlank() && !signUpViewModel.isPwValid(password)) {
                binding.tvSignUpPw.error = getString(R.string.check_pw_valid)
            } else {
                binding.tvSignUpPw.error = null
            }
        }.launchIn(lifecycleScope)
    }

    private fun signUp() {
        signUpViewModel.isSignUpSuccessful.observe(this) {
            Log.e("signup", "${it}")
            if (it) {
                toast(getString(R.string.signup_success))
                startActivity(
                    Intent(
                        this,
                        LoginActivity::class.java,
                    )
                )
                Log.e("btn", "넘어가나?")
            } else {
                toast(getString(R.string.signup_fail))
            }
        }
    }
}
