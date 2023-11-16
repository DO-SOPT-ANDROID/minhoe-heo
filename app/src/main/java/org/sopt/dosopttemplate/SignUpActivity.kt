package org.sopt.dosopttemplate

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import org.sopt.dosopttemplate.databinding.SignUpBinding
import org.sopt.dosopttemplate.request.RequestSignUpDto
import org.sopt.dosopttemplate.response.ResponseSignUpDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: SignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up)
        binding = SignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signUp()

        binding.root.setOnClickListener {
            hideKeyboard()
        }
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
        if (binding.etSignUpId.length() in 6..10 && binding.etSignUpPw.length() in 8..12
            && !binding.etSignUpName.text.isNullOrBlank() && !binding.etSignUpMbti.text.isNullOrBlank()
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

    private fun signUp() {
        val username = binding.etSignUpId.text.toString()
        val password = binding.etSignUpPw.text.toString()
        val nickname = binding.etSignUpName.text.toString()

        binding.signUp.setOnClickListener {
            Log.e("버튼 눌림","btn")
            ServicePool.authService.signUp(RequestSignUpDto(username, password, nickname))
                .enqueue(object : Callback<ResponseSignUpDto> {
                    override fun onResponse(
                        call: Call<ResponseSignUpDto>,
                        response: Response<ResponseSignUpDto>
                    ) {
                        if (response.isSuccessful) {
                            canSignUp()
                            startActivity(intent)
                            Log.e("버튼 성공","btn")
                        }
                        else{
                            Log.e("서버 안되니","btn")
                        }
                    }

                    override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {
                        Toast.makeText(this@SignUpActivity, "서버 에러 발생", Toast.LENGTH_SHORT).show()
                    }
                })
        }
    }
}