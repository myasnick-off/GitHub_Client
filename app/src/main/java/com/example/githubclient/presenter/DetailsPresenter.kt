package com.example.githubclient.presenter

import com.example.githubclient.ui.details.DetailsView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class DetailsPresenter(val router: Router) : MvpPresenter<DetailsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setUserData()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}