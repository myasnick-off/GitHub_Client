package com.example.githubclient.di.modules

import com.example.githubclient.network.GithubApiService
import com.example.githubclient.network.NetworkStatus
import com.example.githubclient.repository.repos.IRoomReposCache
import com.example.githubclient.repository.repos.ReposRepository
import com.example.githubclient.repository.repos.ReposRepositoryImpl
import com.example.githubclient.repository.users.IRoomUsersCache
import com.example.githubclient.repository.users.UsersRepository
import com.example.githubclient.repository.users.UsersRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideUsersRepo(
        apiService: GithubApiService,
        networkStatus: NetworkStatus,
        usersCache: IRoomUsersCache
    ): UsersRepository {
        return UsersRepositoryImpl(apiService, networkStatus, usersCache)
    }

    @Provides
    fun provideReposRepo(
        apiService: GithubApiService,
        networkStatus: NetworkStatus,
        reposCache: IRoomReposCache
    ): ReposRepository {
        return ReposRepositoryImpl(apiService, networkStatus, reposCache)
    }
}