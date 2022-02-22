package com.example.githubclient.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.githubclient.db.dao.GitHubRepoDao
import com.example.githubclient.db.dao.GitHubUserDao
import com.example.githubclient.db.entity.GitHubRepoEntity
import com.example.githubclient.db.entity.GitHubUserEntity

@Database(entities = [GitHubUserEntity::class, GitHubRepoEntity::class], version = 1)
abstract class GitHubDataBase : RoomDatabase() {

    abstract val userDao: GitHubUserDao
    abstract val repoDao: GitHubRepoDao

    companion object {
        private const val  DB_NAME = "database.db"
        private var instance: GitHubDataBase? = null

        fun getInstance(context: Context): GitHubDataBase {
            if (instance == null) {
                instance =
                    Room.databaseBuilder(context, GitHubDataBase::class.java, DB_NAME).build()
            }
            return instance!!
        }
    }
}