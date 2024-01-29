package org.sopt.dosopttemplate.domain.repository

import org.sopt.dosopttemplate.domain.model.Follower

interface FollowerRepository {
    suspend fun loadFollowerData(): Result<List<Follower>>
}
