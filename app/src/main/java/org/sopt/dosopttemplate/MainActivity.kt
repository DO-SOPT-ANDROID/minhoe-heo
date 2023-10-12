package org.sopt.dosopttemplate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.sopt.dosopttemplate.databinding.MainPageBinding
class MainActivity : AppCompatActivity() {
    lateinit var binding: MainPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page)
        binding = MainPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra("id")
        val mbti = intent.getStringExtra("mbti")
        val nickname = intent.getStringExtra("nickname")

        binding.yourId.text = "$id"
        binding.yourMbti.text = "$mbti"
        binding.nickname.text = "$nickname"

    }
}