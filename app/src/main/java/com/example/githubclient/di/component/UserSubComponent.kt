package com.example.githubclient.di.component

import com.example.githubclient.di.modules.UserRepositoryModule
import com.example.githubclient.di.scope.UserScope
import com.example.githubclient.ui.users.UsersPresenter
import com.example.githubclient.ui.users.UsersPresenterFactory
import dagger.Subcomponent

@Subcomponent(modules = [UserRepositoryModule::class])
@UserScope
interface UserSubComponent {

    fun provideRepoSubComponent(): RepoSubComponent

    fun provideUsersPresenterFactory(): UsersPresenterFactory
}