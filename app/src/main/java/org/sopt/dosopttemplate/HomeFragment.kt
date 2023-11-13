package org.sopt.dosopttemplate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import org.sopt.dosopttemplate.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private lateinit var search: SearchView


    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "바인딩에러" }


    private val mockFriendList = listOf<Friend>(
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
            profileImage = R.drawable.img_2,
            name = "스누피친구",
            self_description = "내 이름이 머더라",
        ),
        Friend(
            profileImage = R.drawable.img_3,
            name = "신짱구",
            self_description = "어 이쁜 누나다",
        ),
        Friend(
            profileImage = R.drawable.img_4,
            name = "김철수",
            self_description = "",
        ),
        Friend(
            profileImage = R.drawable.img_5,
            name = "흰둥이",
            self_description = "",
        ),
        Friend(
            profileImage = R.drawable.img_6,
            name = "액션가면",
            self_description = "하하ㅏ",
        ),
        Friend(
            profileImage = R.drawable.img_7,
            name = "부리부리대마왕",
            self_description = "",
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
        val meAdapter = MeAdapter(requireContext())

        friendAdapter.setFriendList(mockFriendList)

        val concatAdapter = ConcatAdapter(meAdapter, friendAdapter)
        binding.rvFriends.adapter = concatAdapter
        binding.fbAddFriend.setOnClickListener {
            Toast.makeText(requireContext(), "친구 추가를 할 수 없습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}


