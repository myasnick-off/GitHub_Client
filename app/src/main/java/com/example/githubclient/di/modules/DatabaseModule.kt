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

@Module
class DatabaseModule {

    @Provides
    fun provideDatabase(context: Context): GitHubDataBase {
        return GitHubDataBase.getInstance(context)
    }

    @Provides
    fun provideUserDao(db : GitHubDataBase): GitHubUserDao {
        return db.userDao
    }

    @Provides
    fun provideRepoDao(db: GitHubDataBase): GitHubRepoDao {
        return db.repoDao
    }

    @Provides
    fun provideUserCache(userDao: GitHubUserDao): IRoomUsersCache {
        return RoomUsersCache(userDao)
    }

    @Provides
    fun provideRepoCache(repoDao: GitHubRepoDao) : IRoomReposCache {
        return RoomReposCache(repoDao)
    }
}