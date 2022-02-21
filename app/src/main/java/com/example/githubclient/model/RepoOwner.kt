package com.example.githubclient.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RepoOwner(
    @SerializedName("id")
    @Expose
    val ownerId: Long
) : Parcelable
