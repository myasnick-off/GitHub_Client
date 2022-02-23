package com.example.githubclient.di.component

import com.example.githubclient.di.modules.*
import com.example.githubclient.ui.details.DetailsPresenter
import com.example.githubclient.ui.details.DetailsPresenterFactory
import com.example.githubclient.ui.main.MainActivity
import com.example.githubclient.ui.main.MainPresenter
import com.example.githubclient.ui.repo.RepoPresenter
import com.example.githubclient.ui.repo.RepoPresenterFactory
import com.example.githubclient.ui.users.UsersPresenter
import dagger.Component
import javax.inject.Singleton


@Component(
    modules = [
        ContextModule::class,
        NavigationModule::class,
        NetworkModule::class,
        DatabaseModule::class,
        RepositoryModule::class
    ]
)

@Singleton
interface AppComponent {

    fun provideMainPresenter(): MainPresenter
    fun provideUsersPresenter(): UsersPresenter

    fun provideDetailsPresenterFactory(): DetailsPresenterFactory
    fun provideRepoPresenterFactory(): RepoPresenterFactory

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(detailsPresenter: DetailsPresenter)
    fun inject(repoPresenter: RepoPresenter)
}