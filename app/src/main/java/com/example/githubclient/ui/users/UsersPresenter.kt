package com.example.githubclient.ui.users

import com.example.githubclient.App
import com.example.githubclient.model.GitHubUser
import com.example.githubclient.repository.users.UsersRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import javax.inject.Inject

class UsersPresenter @Inject constructor(
    private val usersRepository: UsersRepository,
) : MvpPresenter<UsersView>() {


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
    }

    override fun onDestroy() {
        super.onDestroy()
        App.appInstance.destroyUserScope()
    }

    private fun loadData() {
        viewState.showProgress()
        usersRepository.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    viewState.hideProgress()
                    viewState.updateList(it)
                },
                {
                    viewState.hideProgress()
                    viewState.showError(it.message)
                }
            )
    }

    fun onUserClicked(user: GitHubUser) {
       viewState.navigateTo(user)
    }
}