package com.example.githubclient.repository.repos

import com.example.githubclient.model.GitHubRepo
import io.reactivex.rxjava3.core.Single

interface IRoomReposCache {

    fun saveReposToCache(repoList: List<GitHubRepo>)
    fun loadReposFromCache(userId: Long): Single<List<GitHubRepo>>
}