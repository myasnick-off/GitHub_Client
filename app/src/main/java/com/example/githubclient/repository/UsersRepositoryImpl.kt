package com.example.githubclient.repository

import com.example.githubclient.model.GitHubUser
import com.example.githubclient.network.GithubApiService
import io.reactivex.rxjava3.core.Single

class UsersRepositoryImpl(private val githubApiService: GithubApiService): UsersRepository {

    override fun getUsers(): Single<List<GitHubUser>> {
        return githubApiService.getUsers()
    }

}