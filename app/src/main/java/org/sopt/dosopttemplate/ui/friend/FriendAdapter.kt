package org.sopt.dosopttemplate.ui.friend

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.databinding.ItemFriendBinding
import org.sopt.dosopttemplate.domain.model.Friend

class FriendAdapter(context: Context) : RecyclerView.Adapter<FriendViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }
    private var friendList: List<Friend> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val binding = ItemFriendBinding.inflate(inflater, parent, false)
        binding.ivProfile.clipToOutline = true
        return FriendViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        holder.onBind(friendList[position])
    }

    override fun getItemCount() = friendList.size

    fun setFriendList(friendList: List<Friend>) {
        this.friendList = friendList.toList()
        notifyDataSetChanged()
    }
}
