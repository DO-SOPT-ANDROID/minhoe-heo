package org.sopt.dosopttemplate.data.datasource

import org.sopt.dosopttemplate.data.entity.response.ResponseFollowerDto
import org.sopt.dosopttemplate.data.entity.service.ServicePool
import retrofit2.Call

class FollowerDataSource {
    private val followerService = ServicePool.followerService
    suspend fun follower(): Call<ResponseFollowerDto> =
        followerService.follower()
}
