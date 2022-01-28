package com.example.githubclient

import moxy.MvpPresenter

class MainPresenter(private val usersRepository: UsersRepository): MvpPresenter<MainView>() {

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = {
            //TODO: переход на экран пользователя
        }
    }

    private fun loadData() {
        val users = usersRepository.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

}