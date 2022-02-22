package com.example.githubclient.repository.users

import com.example.githubclient.model.GitHubUser
import com.example.githubclient.network.GithubApiService
import com.example.githubclient.network.NetworkStatus
import io.reactivex.rxjava3.core.Single

class UsersRepositoryImpl(
    private val githubApiService: GithubApiService,
    private val networkStatus: NetworkStatus,
    private val roomCache: IRoomUsersCache
) : UsersRepository {

    override fun getUsers(): Single<List<GitHubUser>> = networkStatus.isOnlineSingle()
        .flatMap { isOnline ->
            if (isOnline) {
                githubApiService.getUsers()
                    .flatMap { userList ->
                        roomCache.saveUsersToCache(userList)
                        Single.just(userList)
                    }
            } else {
                roomCache.loadUsersFromCache()
            }
        }
}