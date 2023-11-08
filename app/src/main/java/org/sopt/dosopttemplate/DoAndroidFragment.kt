package org.sopt.dosopttemplate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.sopt.dosopttemplate.databinding.FragmentDoAndroidBinding

class DoAndroidFragment: Fragment() {
    private var _binding : FragmentDoAndroidBinding? = null
    private val binding: FragmentDoAndroidBinding
        get() = requireNotNull(_binding){ "바인딩에러" }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDoAndroidBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
<<<<<<< HEAD
        _binding = null
        super.onDestroyView()
=======
        super.onDestroyView()
        _binding = null
>>>>>>> develop
    }

}