package com.example.githubclient.repository

class UsersRepository {

    private val usersData = (0..99).map { GitHubUser("user$it") }

    fun getUsers() = usersData
}