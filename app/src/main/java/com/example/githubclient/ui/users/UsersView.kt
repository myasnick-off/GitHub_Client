package com.example.githubclient.ui.users

import com.example.githubclient.model.GitHubUser
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

interface UsersView : MvpView {

    @AddToEndSingle
    fun init()

    @AddToEndSingle
    fun updateList(users: List<GitHubUser>)

    @AddToEndSingle
    fun showProgress()

    @AddToEndSingle
    fun hideProgress()

    @Skip
    fun showError(message: String?)
}