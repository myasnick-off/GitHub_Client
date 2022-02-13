package com.example.githubclient.ui.users

import com.example.githubclient.model.GitHubUser
import com.example.githubclient.repository.users.UsersRepository
import com.example.githubclient.ui.IScreens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class UsersPresenter(
    val usersRepository: UsersRepository,
    val router: Router,
    val screens: IScreens
) :
    MvpPresenter<UsersView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
    }

    private fun loadData() {
        usersRepository.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    viewState.updateList(it)
                },
                {
                    viewState.showError(it.message)
                }
        )
    }

    fun onUserClicked(user: GitHubUser) {
        router.navigateTo(screens.detailsScreen(user))
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}