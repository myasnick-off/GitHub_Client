package com.example.githubclient.repository.repos

import com.example.githubclient.db.dao.GitHubRepoDao
import com.example.githubclient.model.GitHubRepo
import com.example.githubclient.utils.repoEntityToModelConverter
import com.example.githubclient.utils.repoModelToEntityConverter
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RoomReposCache @Inject constructor(private val repoDao: GitHubRepoDao): IRoomReposCache {

    override fun saveReposToCache(repoList: List<GitHubRepo>) {
        repoDao.insertAll(repoList.map { repo -> repoModelToEntityConverter(repo) })
    }

    override fun loadReposFromCache(userId: Long): Single<List<GitHubRepo>> {
        return repoDao.getAll(userId)
            .map { entityList ->
                entityList.map { entity -> repoEntityToModelConverter(entity) }
            }
    }
}