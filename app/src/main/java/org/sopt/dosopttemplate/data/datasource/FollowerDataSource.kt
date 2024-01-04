package org.sopt.dosopttemplate.data.datasource

import org.sopt.dosopttemplate.data.entity.response.ResponseFollowerDto
import org.sopt.dosopttemplate.data.entity.service.FollowerService
import javax.inject.Inject

class FollowerDataSource @Inject constructor(
    private val followerService: FollowerService
) {
    suspend fun follower(): ResponseFollowerDto =
        followerService.follower()
}
