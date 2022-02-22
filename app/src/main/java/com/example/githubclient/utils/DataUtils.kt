package com.example.githubclient.utils

import com.example.githubclient.db.entity.GitHubRepoEntity
import com.example.githubclient.db.entity.GitHubUserEntity
import com.example.githubclient.model.GitHubRepo
import com.example.githubclient.model.GitHubUser
import com.example.githubclient.model.RepoOwner

fun userEntityToModelConverter(userEntity: GitHubUserEntity): GitHubUser {
    return GitHubUser(
        id = userEntity.id,
        login = userEntity.login,
        avatarUrl = userEntity.avatarUrl,
        reposUrl = userEntity.reposUrl
    )
}

fun userModelToEntityConverter(user: GitHubUser): GitHubUserEntity {
    return GitHubUserEntity(
        id = user.id,
        login = user.login,
        avatarUrl = user.avatarUrl,
        reposUrl = user.reposUrl
    )
}

fun repoEntityToModelConverter(repoEntity: GitHubRepoEntity): GitHubRepo {
    return GitHubRepo(
        id = repoEntity.id,
        name = repoEntity.name,
        updatedAt = repoEntity.updatedAt,
        description = repoEntity.description,
        language = repoEntity.language,
        visibility = repoEntity.visibility,
        stargazersCount = repoEntity.stargazersCount,
        watchersCount = repoEntity.watchersCount,
        forksCount = repoEntity.forksCount,
        owner = RepoOwner(repoEntity.userId)
    )
}

fun repoModelToEntityConverter(repo: GitHubRepo): GitHubRepoEntity {
    return GitHubRepoEntity(
        id = repo.id,
        userId = repo.owner.ownerId,
        name = repo.name,
        updatedAt = repo.updatedAt,
        description = repo.description,
        language = repo.language,
        visibility = repo.visibility,
        stargazersCount = repo.stargazersCount,
        watchersCount = repo.watchersCount,
        forksCount = repo.forksCount
    )
}