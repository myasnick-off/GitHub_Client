package com.example.githubclient.model

import com.google.gson.annotations.Expose

data class GitHubRepo(
    @Expose val name: String,
    @Expose val updatedAt: String,
    @Expose val description: String?,
    @Expose val language: String,
    @Expose val visibility: String,
    @Expose val stargazersCount: Int,
    @Expose val watchersCount: Int,
    @Expose val forksCount: Int
)
