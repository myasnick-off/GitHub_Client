package com.example.githubclient

import moxy.MvpView

interface MainView : MvpView {
    fun init()
    fun updateList()
}