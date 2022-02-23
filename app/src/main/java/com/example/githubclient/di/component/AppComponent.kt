package com.example.githubclient.di.component

import com.example.githubclient.di.modules.*
import com.example.githubclient.ui.details.DetailsPresenter
import com.example.githubclient.ui.main.MainActivity
import com.example.githubclient.ui.main.MainPresenter
import com.example.githubclient.ui.repo.RepoPresenter
import com.example.githubclient.ui.users.UsersPresenter
import dagger.Component


@Component(
    modules = [
        ContextModule::class,
        NavigationModule::class,
        NetworkModule::class,
        DatabaseModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(detailsPresenter: DetailsPresenter)
    fun inject(repoPresenter: RepoPresenter)
}