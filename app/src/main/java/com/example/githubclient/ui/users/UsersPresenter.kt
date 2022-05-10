package com.example.githubclient.ui.users

import com.example.githubclient.App
import com.example.githubclient.model.GitHubRepo
import com.example.githubclient.model.GitHubUser
import com.example.githubclient.repository.users.UsersRepository
import com.example.githubclient.ui.repo.RepoPresenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import javax.inject.Inject

class UsersPresenter @AssistedInject constructor(
    private val usersRepository: UsersRepository,
    @Assisted private val view: UsersView
) : MvpPresenter<UsersView>() {


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        view.init()
        loadData()
    }

    override fun onDestroy() {
        super.onDestroy()
        App.appInstance.destroyUserScope()
    }

    fun loadData() {
        view.showProgress()
        usersRepository.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.hideProgress()
                    view.updateList(it)
                },
                {
                    view.hideProgress()
                    view.showError(it.message)
                }
            )
    }

    fun onUserClicked(user: GitHubUser) {
       view.navigateTo(user)
    }
}

@AssistedFactory
interface UsersPresenterFactory {
    fun presenter(view: UsersView): UsersPresenter
}