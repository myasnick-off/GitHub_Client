package com.example.githubclient.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class GitHubUser(
    @Expose val id: Long,
    @Expose val login: String,
    @Expose val avatarUrl: String?
    ) : Parcelable
