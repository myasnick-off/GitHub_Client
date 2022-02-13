package com.example.githubclient.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class GitHubRepo(
    @Expose val name: String,
    @Expose val updatedAt: String,
    @Expose val description: String?,
    @Expose val language: String,
    @Expose val visibility: String,
    @Expose val stargazersCount: Int,
    @Expose val watchersCount: Int,
    @Expose val forksCount: Int
) : Parcelable
