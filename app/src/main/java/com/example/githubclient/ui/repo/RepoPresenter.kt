package com.example.githubclient.ui.repo

import com.example.githubclient.model.GitHubRepo
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class RepoPresenter(private val repo: GitHubRepo, val router: Router) : MvpPresenter<RepoView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init(repo)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}