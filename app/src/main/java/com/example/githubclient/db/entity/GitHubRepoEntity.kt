package com.example.githubclient.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = GitHubUserEntity::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class GitHubRepoEntity(
    @PrimaryKey val id: Long,
    val userId: Long,
    val name: String,
    val updatedAt: String,
    val description: String?,
    val language: String?,
    val visibility: String,
    val stargazersCount: Int,
    val watchersCount: Int,
    val forksCount: Int
)
