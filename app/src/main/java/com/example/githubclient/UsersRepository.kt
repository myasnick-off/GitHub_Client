package com.example.githubclient

class UsersRepository {

    private val usersData = listOf(
        GitHubUser("user1"),
        GitHubUser("user2"),
        GitHubUser("user3"),
        GitHubUser("user4"),
        GitHubUser("user5"),
        GitHubUser("user6"),
        GitHubUser("user7"),
        GitHubUser("user8"),
        GitHubUser("user9")
    )

    fun getUsers() = usersData
}