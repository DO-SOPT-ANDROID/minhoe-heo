package org.sopt.dosopttemplate.presentation.home

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.DialogBinding

class CustomDialog : DialogFragment() {
    private var _binding: DialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogBinding.inflate(inflater, container, false)
        val view = binding.root
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setBackgroundDrawableResource(R.drawable.dialog_rounded_corners)

        binding.btCcl.setOnClickListener {
            dismiss()
        }
        binding.btOk.setOnClickListener {
            dismiss()
        }

        return view
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
