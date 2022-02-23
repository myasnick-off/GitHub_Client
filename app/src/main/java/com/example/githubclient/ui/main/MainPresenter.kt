package com.example.githubclient.ui.main

import com.example.githubclient.App
import com.example.githubclient.ui.IScreens
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter : MvpPresenter<MainView>() {

    @Inject
    lateinit var router: Router
    @Inject
    lateinit var screens: IScreens

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        App.appInstance.appComponent.inject(this)
        router.replaceScreen(screens.usersScreen())
    }

    fun backPressed() {
        router.exit()
    }

}