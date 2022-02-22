package com.example.githubclient.repository.repos

import com.example.githubclient.model.GitHubRepo
import com.example.githubclient.model.GitHubUser
import com.example.githubclient.network.GithubApiService
import com.example.githubclient.network.NetworkStatus
import io.reactivex.rxjava3.core.Single

class ReposRepositoryImpl(
    private val githubApiService: GithubApiService,
    private val networkStatus: NetworkStatus,
    private val roomCache: IRoomReposCache
) : ReposRepository {

    override fun getRepos(user: GitHubUser): Single<List<GitHubRepo>> = networkStatus.isOnlineSingle()
        .flatMap { isOnline ->
            if (isOnline) {
                githubApiService.getRepos(user.reposUrl)
                    .flatMap { repoList ->
                        roomCache.saveReposToCache(repoList)
                        Single.just(repoList)
                    }
            } else {
                roomCache.loadReposFromCache(user.id)
            }
        }
}