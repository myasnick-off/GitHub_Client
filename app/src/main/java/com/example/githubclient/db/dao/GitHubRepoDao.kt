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

    @Query("SELECT * FROM GitHubRepoEntity")
    fun getAll(): Single<List<GitHubRepoEntity>>
}