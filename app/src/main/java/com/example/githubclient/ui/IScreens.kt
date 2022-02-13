package com.example.githubclient.ui

import android.os.Bundle
import com.example.githubclient.model.GitHubUser
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun usersScreen(): Screen
    fun detailsScreen(user: GitHubUser): Screen
}