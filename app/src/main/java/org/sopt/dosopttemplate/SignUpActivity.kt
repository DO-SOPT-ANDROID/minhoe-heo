package org.sopt.dosopttemplate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.sopt.dosopttemplate.databinding.SignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: SignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up)
        binding = SignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signUp.setOnClickListener {
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
    }
}