package com.example.githubclient.ui.repo

import com.example.githubclient.model.GitHubRepo
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import moxy.MvpPresenter

class RepoPresenter @AssistedInject constructor(
    @Assisted private val repo: GitHubRepo,
    ) : MvpPresenter<RepoView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init(repo)
    }
}

@AssistedFactory
interface RepoPresenterFactory {
    fun presenter(repo: GitHubRepo): RepoPresenter
}