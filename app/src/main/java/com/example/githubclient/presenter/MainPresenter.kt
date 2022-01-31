package com.example.githubclient.presenter

import com.example.githubclient.ui.IScreens
import com.example.githubclient.ui.main.MainView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class MainPresenter(val router: Router, val screens: IScreens) :
    MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }

    fun backPressed() {
        router.exit()
    }

}