package com.example.githubclient.ui.repo

import com.example.githubclient.App
import com.example.githubclient.model.GitHubRepo
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class RepoPresenter(private val repo: GitHubRepo) : MvpPresenter<RepoView>() {

    @Inject
    lateinit var router: Router

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