package com.example.githubclient.repository.repos

import com.example.githubclient.model.GitHubRepo
import com.example.githubclient.model.GitHubUser
import io.reactivex.rxjava3.core.Single

interface ReposRepository {
    fun getRepos(user: GitHubUser): Single<List<GitHubRepo>>
}