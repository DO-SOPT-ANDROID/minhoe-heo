package org.sopt.dosopttemplate.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.dosopttemplate.data.entity.service.FollowerService
import org.sopt.dosopttemplate.data.entity.service.LoginService
import org.sopt.dosopttemplate.data.entity.service.SignUpService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Singleton
    @Provides
    fun provideLoginService(retrofit: Retrofit): LoginService =
        retrofit.create(LoginService::class.java)

    @Singleton
    @Provides
    fun provideSignUpService(retrofit: Retrofit): SignUpService =
        retrofit.create(SignUpService::class.java)

    @Singleton
    @Provides
    fun provideFollowerService(retrofit: Retrofit): FollowerService =
        retrofit.create(FollowerService::class.java)
}
