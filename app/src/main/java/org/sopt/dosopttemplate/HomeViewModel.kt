package org.sopt.dosopttemplate

import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    val mockFriendList = listOf<Friend>(
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
}