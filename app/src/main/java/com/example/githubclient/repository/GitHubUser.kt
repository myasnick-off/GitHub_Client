package com.example.githubclient.repository

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GitHubUser(val login: String) : Parcelable
