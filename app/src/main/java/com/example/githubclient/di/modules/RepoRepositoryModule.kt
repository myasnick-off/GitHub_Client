package com.example.githubclient.di.modules

import com.example.githubclient.di.scope.RepoScope
import com.example.githubclient.repository.repos.ReposRepository
import com.example.githubclient.repository.repos.ReposRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface RepoRepositoryModule {

    @Binds
    @RepoScope
    fun provideRepoRepository(impl: ReposRepositoryImpl): ReposRepository
}