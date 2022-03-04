package com.example.githubclient.ui.users

import com.example.githubclient.model.GitHubUser
import com.example.githubclient.repository.users.UsersRepository
import com.example.githubclient.ui.IScreens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import javax.inject.Inject

class UsersPresenter @Inject constructor(
    private val usersRepository: UsersRepository,
    private val router: Router,
    private val screens: IScreens
) : MvpPresenter<UsersView>() {


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
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
        router.navigateTo(screens.detailsScreen(user))
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}