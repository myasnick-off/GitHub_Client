package com.example.githubclient.ui

import android.os.Bundle
import com.example.githubclient.ui.details.DetailsFragment
import com.example.githubclient.ui.users.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun details(bundle: Bundle) = FragmentScreen { DetailsFragment.newInstance(bundle) }
}