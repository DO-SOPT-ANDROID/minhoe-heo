package org.sopt.dosopttemplate.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.dosopttemplate.data.repositoryimpl.FollowerRepositoryImpl
import org.sopt.dosopttemplate.data.repositoryimpl.LoginRepositoryImpl
import org.sopt.dosopttemplate.data.repositoryimpl.SignUpRepositoryImpl
import org.sopt.dosopttemplate.domain.repository.FollowerRepository
import org.sopt.dosopttemplate.domain.repository.LoginRepository
import org.sopt.dosopttemplate.domain.repository.SignUpRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindFollowerRepository(
        followerRepositoryImpl: FollowerRepositoryImpl
    ): FollowerRepository

    @Binds
    @Singleton
    fun bindLoginRepository(
        loginRepositoryImpl: LoginRepositoryImpl
    ): LoginRepository

    @Binds
    @Singleton
    fun bindSignUpRepository(
        signUpRepositoryImpl: SignUpRepositoryImpl
    ): SignUpRepository
}
