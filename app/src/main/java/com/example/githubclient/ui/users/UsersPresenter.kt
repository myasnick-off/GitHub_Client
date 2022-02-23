package com.example.githubclient.ui.users

import com.example.githubclient.App
import com.example.githubclient.model.GitHubUser
import com.example.githubclient.repository.users.UsersRepository
import com.example.githubclient.ui.IScreens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import javax.inject.Inject

class UsersPresenter : MvpPresenter<UsersView>() {

    @Inject
    lateinit var usersRepository: UsersRepository
    @Inject
    lateinit var router: Router
    @Inject
    lateinit var screens: IScreens

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        App.appInstance.appComponent.inject(this)
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