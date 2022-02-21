package com.example.githubclient.repository.users

import com.example.githubclient.db.dao.GitHubUserDao
import com.example.githubclient.model.GitHubUser
import com.example.githubclient.network.GithubApiService
import com.example.githubclient.network.NetworkStatus
import com.example.githubclient.utils.userEntityToModelConverter
import com.example.githubclient.utils.userModelToEntityConverter
import io.reactivex.rxjava3.core.Single

class UsersRepositoryImpl(
    private val githubApiService: GithubApiService,
    private val userDao: GitHubUserDao,
    private val networkStatus: NetworkStatus
) : UsersRepository {

    override fun getUsers(): Single<List<GitHubUser>> = networkStatus.isOnlineSingle()
        .flatMap { isOnline ->
            if (isOnline) {
                githubApiService.getUsers()
                    .flatMap { userList ->
                        userDao.insertAll(userList.map { user -> userModelToEntityConverter(user) })
                        Single.just(userList)
                    }
            } else {
                userDao.getAll()
                    .map { entityList ->
                        entityList.map { entity -> userEntityToModelConverter(entity) }
                    }
            }
        }

}