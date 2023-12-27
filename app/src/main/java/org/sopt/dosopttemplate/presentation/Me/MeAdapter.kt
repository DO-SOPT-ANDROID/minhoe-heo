package org.sopt.dosopttemplate.presentation.Me

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.databinding.ItemMeBinding

class MeAdapter(context: Context) : RecyclerView.Adapter<MeViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeViewHolder {
        val binding = ItemMeBinding.inflate(inflater, parent, false)
        binding.ivMyProfile.clipToOutline = true
        return MeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MeViewHolder, position: Int) {
    }

    override fun getItemCount(): Int = 1

}
