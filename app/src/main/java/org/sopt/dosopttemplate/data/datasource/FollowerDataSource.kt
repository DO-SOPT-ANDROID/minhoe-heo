package org.sopt.dosopttemplate.data.datasource

import org.sopt.dosopttemplate.data.entity.response.ResponseFollowerDto
import org.sopt.dosopttemplate.data.entity.service.ServicePool

class FollowerDataSource {
    private val followerService = ServicePool.followerService
    suspend fun follower(): ResponseFollowerDto =
        followerService.follower()
}
