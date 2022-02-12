package com.example.githubclient.repository

import io.reactivex.rxjava3.core.Observable

class UsersRepository {

    private val usersData = (0..99).map { GitHubUser("User_$it") }

    fun getUsers(): Observable<GitHubUser> {
        return Observable.fromIterable(usersData)
    }
}