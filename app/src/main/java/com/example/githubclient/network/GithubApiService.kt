package com.example.githubclient.network

import com.example.githubclient.model.GitHubRepo
import com.example.githubclient.model.GitHubUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface GithubApiService {

    @GET("/users")
    fun getUsers(): Single<List<GitHubUser>>

    @GET
    fun getRepos(@Url url: String): Single<List<GitHubRepo>>
}