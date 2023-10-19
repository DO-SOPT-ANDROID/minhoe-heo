package org.sopt.dosopttemplate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.sopt.dosopttemplate.databinding.FragmentHomeBinding

class HomeFragment: Fragment() {
    private var _binding : FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding){ "바인딩에러" }

    private val mockFriendList =listOf<Friend>(
        Friend(
            profileImage = R.drawable.img,
            name = "라이언",
            self_description = "",
        ),
        Friend(
            profileImage = R.drawable.img_1,
            name = "양파쿵야",
            self_description = "돈벌기 힘들다",
        ),
        Friend(
            profileImage = R.drawable.quokka,
            name = "쿼카",
            self_description = "안녕 나는 귀여운 쿼카야",
        ),
        Friend(
            profileImage = R.drawable.img_2,
            name = "스누피친구",
            self_description = "내 이름이 머더라",
        )
    )

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
        binding.rvFriends.adapter= friendAdapter
        friendAdapter.setFriendList(mockFriendList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}