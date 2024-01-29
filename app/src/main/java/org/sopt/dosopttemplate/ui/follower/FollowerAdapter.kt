package org.sopt.dosopttemplate.ui.follower

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.dosopttemplate.databinding.ItemFollowerBinding
import org.sopt.dosopttemplate.domain.model.Follower
import org.sopt.dosopttemplate.util.extension.ItemDiffCallback


class FollowerAdapter() :
    ListAdapter<Follower, FollowerAdapter.FollowerViewHolder>(
        ItemDiffCallback<Follower>(
            onItemsTheSame = { old, new -> old == new },
            onContentsTheSame = { old, new -> old == new }
        )
    ) {

    class FollowerViewHolder(private val binding: ItemFollowerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Follower) {
            binding.ivAvatar.load(data.avatar)
            binding.tvEmail.text = data.email
            binding.tvName.text = data.firstName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val binding =
            ItemFollowerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }
}
