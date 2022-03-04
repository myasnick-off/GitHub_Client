package com.example.githubclient.di.modules

import android.content.Context
import com.example.githubclient.db.GitHubDataBase
import com.example.githubclient.db.dao.GitHubRepoDao
import com.example.githubclient.db.dao.GitHubUserDao
import com.example.githubclient.repository.repos.IRoomReposCache
import com.example.githubclient.repository.repos.RoomReposCache
import com.example.githubclient.repository.users.IRoomUsersCache
import com.example.githubclient.repository.users.RoomUsersCache
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): GitHubDataBase {
        return GitHubDataBase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideUserDao(db : GitHubDataBase): GitHubUserDao {
        return db.userDao
    }

    @Provides
    @Singleton
    fun provideRepoDao(db: GitHubDataBase): GitHubRepoDao {
        return db.repoDao
    }

    @Provides
    @Singleton
    fun provideUserCache(userDao: GitHubUserDao): IRoomUsersCache {
        return RoomUsersCache(userDao)
    }

    @Provides
    @Singleton
    fun provideRepoCache(repoDao: GitHubRepoDao) : IRoomReposCache {
        return RoomReposCache(repoDao)
    }
}