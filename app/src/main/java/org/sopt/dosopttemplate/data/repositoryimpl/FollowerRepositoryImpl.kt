package org.sopt.dosopttemplate.data.repositoryimpl

import org.sopt.dosopttemplate.data.datasource.FollowerDataSource
import org.sopt.dosopttemplate.domain.model.Follower
import org.sopt.dosopttemplate.domain.repository.FollowerRepository
import javax.inject.Inject

class FollowerRepositoryImpl @Inject constructor(private val followerDataSource: FollowerDataSource) :
    FollowerRepository {
    override suspend fun loadFollowerData(): Result<List<Follower>> =
        runCatching {
            followerDataSource.follower().toFollower()
        }
}


