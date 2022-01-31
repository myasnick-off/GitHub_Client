package com.example.githubclient.presenter

import android.os.Bundle
import com.example.githubclient.ui.users.UsersView
import com.example.githubclient.repository.UsersRepository
import com.example.githubclient.ui.IScreens
import com.example.githubclient.ui.details.DetailsFragment.Companion.KEY_USER
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UsersPresenter(val usersRepository: UsersRepository, val router: Router, val screens: IScreens) :
    MvpPresenter<UsersView>() {

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            val bundle = Bundle().apply {
                this.putParcelable(KEY_USER, usersListPresenter.users[itemView.pos])
            }
            router.navigateTo(screens.details(bundle))
        }
    }

    private fun loadData() {
        val users = usersRepository.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}