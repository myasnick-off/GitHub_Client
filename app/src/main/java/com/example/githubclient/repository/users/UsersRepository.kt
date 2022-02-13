package com.example.githubclient.repository.users

import com.example.githubclient.model.GitHubUser
import io.reactivex.rxjava3.core.Single

interface UsersRepository {
    fun getUsers(): Single<List<GitHubUser>>
}