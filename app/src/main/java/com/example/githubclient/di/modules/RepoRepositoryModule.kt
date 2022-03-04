package com.example.githubclient.di.modules

import com.example.githubclient.di.scope.RepoScope
import com.example.githubclient.repository.repos.IRoomReposCache
import com.example.githubclient.repository.repos.ReposRepository
import com.example.githubclient.repository.repos.ReposRepositoryImpl
import com.example.githubclient.repository.repos.RoomReposCache
import dagger.Binds
import dagger.Module

@Module
interface RepoRepositoryModule {

    @Binds
    @RepoScope
    fun provideRepoRepository(impl: ReposRepositoryImpl): ReposRepository

    @Binds
    @RepoScope
    fun provideRepoCache(impl: RoomReposCache) : IRoomReposCache
}