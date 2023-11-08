package org.sopt.dosopttemplate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import org.sopt.dosopttemplate.databinding.FragmentHomeBinding
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "바인딩에러" }

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val friendAdapter = FriendAdapter(requireContext())
        val meAdapter = MeAdapter(requireContext())


        friendAdapter.setFriendList(viewModel.mockFriendList)

        val concatAdapter = ConcatAdapter(meAdapter, friendAdapter)
        binding.rvFriends.adapter = concatAdapter

        binding.fbAddFriend.setOnClickListener {
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}