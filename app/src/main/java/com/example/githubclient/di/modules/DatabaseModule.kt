package com.example.githubclient.di.modules

import android.content.Context
import com.example.githubclient.db.GitHubDataBase
import com.example.githubclient.db.dao.GitHubRepoDao
import com.example.githubclient.db.dao.GitHubUserDao
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
    fun  provideRepoDao(db: GitHubDataBase): GitHubRepoDao {
        return db.repoDao
    }
}