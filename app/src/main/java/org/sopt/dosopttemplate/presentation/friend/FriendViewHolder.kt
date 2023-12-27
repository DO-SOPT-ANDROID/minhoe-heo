package org.sopt.dosopttemplate.presentation.friend

import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.databinding.ItemFriendBinding
import org.sopt.dosopttemplate.domain.model.Friend

class FriendViewHolder(private val binding: ItemFriendBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(friendData: Friend) {
        binding.ivProfile.setImageResource(friendData.profileImage)
        binding.tvName.text = friendData.name
        binding.tvSelfDecription.text = friendData.self_description
    }
}
