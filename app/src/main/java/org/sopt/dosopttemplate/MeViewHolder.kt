package org.sopt.dosopttemplate

import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.databinding.ItemMeBinding

class MeViewHolder(private val binding: ItemMeBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(MeData: Me) {
        binding.ivMyProfile.setImageResource(MeData.profileImage)
        binding.tvMyName.text= MeData.name
        binding.tvMySelfDecription.text= MeData.self_description
    }
}