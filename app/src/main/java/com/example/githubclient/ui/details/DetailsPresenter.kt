package com.example.githubclient.ui.details

import com.example.githubclient.App
import com.example.githubclient.model.GitHubRepo
import com.example.githubclient.model.GitHubUser
import com.example.githubclient.repository.repos.ReposRepository
import com.example.githubclient.ui.IScreens
import com.github.terrakok.cicerone.Router
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import javax.inject.Inject

class DetailsPresenter (private val user: GitHubUser) : MvpPresenter<DetailsView>() {

    @Inject
    lateinit var repository: ReposRepository
    @Inject
    lateinit var router: Router
    @Inject
    lateinit var screens: IScreens

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        App.appInstance.appComponent.inject(this)
        viewState.initUserData(user)
        viewState.initRepoList()
        loadData()
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

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    fun onItemClicked(repo: GitHubRepo) {
        router.navigateTo(screens.repoScreen(repo))
    }
}

//@AssistedFactory
//interface DetailsPresenterFactory {
//    fun presenter(user: GitHubUser): DetailsPresenter
//}