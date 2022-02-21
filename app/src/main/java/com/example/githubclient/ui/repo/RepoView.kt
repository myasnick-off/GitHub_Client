package com.example.githubclient.ui.repo

import com.example.githubclient.model.GitHubRepo
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface RepoView : MvpView {

    @AddToEndSingle
    fun init(repo: GitHubRepo)
}