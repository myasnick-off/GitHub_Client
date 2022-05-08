package com.example.githubclient.ui.details

import com.example.githubclient.App
import com.example.githubclient.model.GitHubRepo
import com.example.githubclient.model.GitHubUser
import com.example.githubclient.repository.repos.ReposRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class DetailsPresenter @AssistedInject constructor(
    @Assisted private val user: GitHubUser,
    private val repository: ReposRepository,
) : MvpPresenter<DetailsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initUserData(user)
        viewState.initRepoList()
        loadData()
    }

    override fun onDestroy() {
        super.onDestroy()
        App.appInstance.destroyRepoScope()
    }

    private fun loadData() {
        repository.getRepos(user)
            .doOnSubscribe { viewState.showProgress() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    viewState.hideProgress()
                    viewState.updateRepoList(it)
                },
                {
                    viewState.hideProgress()
                    viewState.showError(it.message)
                }
            )
    }

    fun onItemClicked(repo: GitHubRepo) {
       viewState.navigateTo(repo)
    }
}

@AssistedFactory
interface DetailsPresenterFactory {
    fun presenter(user: GitHubUser): DetailsPresenter
}