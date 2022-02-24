package com.example.githubclient.di.modules

import com.example.githubclient.repository.repos.ReposRepository
import com.example.githubclient.repository.repos.ReposRepositoryImpl
import com.example.githubclient.repository.users.UsersRepository
import com.example.githubclient.repository.users.UsersRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun provideUserRepository(impl: UsersRepositoryImpl): UsersRepository

    @Binds
    @Singleton
    fun provideRepoRepository(impl: ReposRepositoryImpl): ReposRepository
}