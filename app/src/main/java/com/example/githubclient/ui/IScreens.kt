package com.example.githubclient.ui

import com.example.githubclient.model.GitHubRepo
import com.example.githubclient.model.GitHubUser
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun usersScreen(): Screen
    fun detailsScreen(user: GitHubUser): Screen
    fun repoScreen(repo: GitHubRepo): Screen
}