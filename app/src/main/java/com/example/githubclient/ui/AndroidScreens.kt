package com.example.githubclient.ui

import com.example.githubclient.model.GitHubRepo
import com.example.githubclient.model.GitHubUser
import com.example.githubclient.ui.details.DetailsFragment
import com.example.githubclient.ui.repo.RepoFragment
import com.example.githubclient.ui.users.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun usersScreen() = FragmentScreen { UsersFragment.newInstance() }
    override fun detailsScreen(user: GitHubUser) = FragmentScreen { DetailsFragment.newInstance(user) }
    override fun repoScreen(repo: GitHubRepo) = FragmentScreen { RepoFragment.newInstance(repo) }
}