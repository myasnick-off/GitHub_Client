package com.example.githubclient.repository.repos

import com.example.githubclient.model.GitHubRepo
import io.reactivex.rxjava3.core.Single

interface ReposRepository {
    fun getRepos(url: String): Single<List<GitHubRepo>>
}