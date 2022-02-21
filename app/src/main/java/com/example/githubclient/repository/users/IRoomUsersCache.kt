package com.example.githubclient.repository.users

import com.example.githubclient.model.GitHubUser
import io.reactivex.rxjava3.core.Single

interface IRoomUsersCache {

    fun saveUsersToCache(userList: List<GitHubUser>)
    fun loadUsersFromCache():  Single<List<GitHubUser>>
}