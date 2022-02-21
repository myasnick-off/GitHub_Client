package com.example.githubclient.repository.repos

import com.example.githubclient.db.dao.GitHubRepoDao
import com.example.githubclient.model.GitHubRepo
import com.example.githubclient.model.GitHubUser
import com.example.githubclient.network.GithubApiService
import com.example.githubclient.network.NetworkStatus
import com.example.githubclient.utils.repoEntityToModelConverter
import com.example.githubclient.utils.repoModelToEntityConverter
import io.reactivex.rxjava3.core.Single

class ReposRepositoryImpl(
    private val githubApiService: GithubApiService,
    private val repoDao: GitHubRepoDao,
    private val networkStatus: NetworkStatus
) : ReposRepository {

    override fun getRepos(user: GitHubUser): Single<List<GitHubRepo>> = networkStatus.isOnlineSingle()
        .flatMap { isOnline ->
            if (isOnline) {
                githubApiService.getRepos(user.reposUrl)
                    .flatMap { repoList ->
                        repoDao.insertAll(repoList.map { repo -> repoModelToEntityConverter(repo) })
                        Single.just(repoList)
                    }
            } else {
                repoDao.getAll(user.id)
                    .map { entityList ->
                        entityList.map { entity -> repoEntityToModelConverter(entity) }
                    }
            }
        }
}