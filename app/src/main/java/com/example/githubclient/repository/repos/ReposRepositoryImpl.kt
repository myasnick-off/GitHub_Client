package com.example.githubclient.repository.repos

import com.example.githubclient.model.GitHubRepo
import com.example.githubclient.network.GithubApiService
import io.reactivex.rxjava3.core.Single

class ReposRepositoryImpl(private val githubApiService: GithubApiService) : ReposRepository {

    override fun getRepos(url: String): Single<List<GitHubRepo>> {
        return githubApiService.getRepos(url)
    }
}