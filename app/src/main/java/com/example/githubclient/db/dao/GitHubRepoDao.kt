package com.example.githubclient.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubclient.db.entity.GitHubRepoEntity
import io.reactivex.rxjava3.core.Single

@Dao
interface GitHubRepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repo: GitHubRepoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(repos: List<GitHubRepoEntity>)

    @Query("SELECT * FROM GitHubRepoEntity WHERE userId = :userId")
    fun getAll(userId: Long): Single<List<GitHubRepoEntity>>
}