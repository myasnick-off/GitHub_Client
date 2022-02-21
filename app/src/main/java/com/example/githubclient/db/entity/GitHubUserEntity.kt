package com.example.githubclient.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GitHubUserEntity(
    @PrimaryKey val id: Long,
    val login: String,
    val avatarUrl: String?,
    val reposUrl: String
)