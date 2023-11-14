package org.sopt.dosopttemplate

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.ServicePool.authService
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
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

        binding.signIn.setOnClickListener {
            if (binding.etSignInId.text.toString() == id && binding.etSignInPw.text.toString() == pw) {
                Toast.makeText(this, "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show()

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

    private fun setResult() {
        activityResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                id = result.data?.getStringExtra("id") ?: ""
                pw = result.data?.getStringExtra("pw") ?: ""
                nickname = result.data?.getStringExtra("nickname") ?: ""
                mbti = result.data?.getStringExtra("mbti") ?: ""
            }
        }
    }

    private fun login() {
        val id = binding.etSignInId.text.toString()
        val password = binding.etSignInPw.text.toString()

        binding.signIn.setOnClickListener {
            authService.login(RequestLoginDto(id, password))
                .enqueue(object : retrofit2.Callback<ResponseLoginDto> {
                    override fun onResponse(
                        call: Call<ResponseLoginDto>,
                        response: Response<ResponseLoginDto>,
                    ) {
                        if (response.isSuccessful) {
                            val data: ResponseLoginDto = response.body()!!
                            val userId = data.id
                            Toast.makeText(
                                this@LoginActivity,
                                "로그인이 성공하였고 유저의 ID는 $userId 입니둥",
                                Toast.LENGTH_SHORT,
                            ).show()

                            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                            startActivity(intent)
                        }
                    }

                    override fun onFailure(call: Call<ResponseLoginDto>, t: Throwable) {
                        Toast.makeText(this@LoginActivity, "서버 에러 발생", Toast.LENGTH_SHORT).show()
                    }
                })
        }
    }
}