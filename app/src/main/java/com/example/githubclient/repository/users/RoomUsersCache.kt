package com.example.githubclient.repository.users

import com.example.githubclient.db.dao.GitHubUserDao
import com.example.githubclient.model.GitHubUser
import com.example.githubclient.utils.userEntityToModelConverter
import com.example.githubclient.utils.userModelToEntityConverter
import io.reactivex.rxjava3.core.Single

class RoomUsersCache(private val userDao: GitHubUserDao) : IRoomUsersCache {

    override fun saveUsersToCache(userList: List<GitHubUser>) {
        userDao.insertAll(userList.map { user -> userModelToEntityConverter(user) })
    }

    override fun loadUsersFromCache(): Single<List<GitHubUser>> {
        return userDao.getAll()
            .map { entityList ->
                entityList.map { entity -> userEntityToModelConverter(entity) }
            }
    }
}