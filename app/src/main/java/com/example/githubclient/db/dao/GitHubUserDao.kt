package com.example.githubclient.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubclient.db.entity.GitHubUserEntity
import io.reactivex.rxjava3.core.Single

@Dao
interface GitHubUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: GitHubUserEntity)

    @Query("SELECT * FROM GitHubUserEntity")
    fun getAll(): Single<List<GitHubUserEntity>>
}