package org.sopt.dosopttemplate

import androidx.annotation.DrawableRes

data class Me(
    @DrawableRes val profileImage: Int,
    val name: String,
    val self_description: String
)

