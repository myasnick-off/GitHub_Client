package com.example.githubclient.ui.repo

import com.example.githubclient.App
import com.example.githubclient.model.GitHubRepo
import com.github.terrakok.cicerone.Router
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import moxy.MvpPresenter
import javax.inject.Inject

class RepoPresenter @AssistedInject constructor(
    @Assisted private val repo: GitHubRepo,
    private val router: Router
    ) : MvpPresenter<RepoView>() {



    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        App.appInstance.appComponent.inject(this)
        viewState.init(repo)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}

@AssistedFactory
interface RepoPresenterFactory {
    fun presenter(repo: GitHubRepo): RepoPresenter
}