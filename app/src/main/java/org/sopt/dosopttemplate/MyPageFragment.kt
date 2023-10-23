package org.sopt.dosopttemplate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.sopt.dosopttemplate.databinding.FragmentMyPageBinding

class MyPageFragment: Fragment() {
    private var _binding : FragmentMyPageBinding? = null
    private val binding: FragmentMyPageBinding
        get() = requireNotNull(_binding){ "바인딩에러" }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyPageBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = requireActivity().intent.getStringExtra("id")
        val mbti = requireActivity().intent.getStringExtra("mbti")
        val nickname = requireActivity().intent.getStringExtra("nickname")

        binding.yourId.text = id
        binding.yourMbti.text = mbti
        binding.nickname.text = nickname

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}