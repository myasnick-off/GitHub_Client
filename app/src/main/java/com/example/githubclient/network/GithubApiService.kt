package com.example.githubclient.network

import com.example.githubclient.model.GitHubUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface GithubApiService {

    @GET("/users")
    fun getUsers(): Single<List<GitHubUser>>
}