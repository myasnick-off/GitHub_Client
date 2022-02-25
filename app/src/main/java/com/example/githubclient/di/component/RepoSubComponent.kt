package com.example.githubclient.di.component

import com.example.githubclient.di.modules.RepoRepositoryModule
import com.example.githubclient.di.scope.RepoScope
import com.example.githubclient.ui.details.DetailsPresenterFactory
import com.example.githubclient.ui.repo.RepoPresenterFactory
import dagger.Subcomponent

@Subcomponent( modules = [RepoRepositoryModule::class])
@RepoScope
interface RepoSubComponent {

    fun provideDetailsPresenterFactory(): DetailsPresenterFactory

    fun provideRepoPresenterFactory(): RepoPresenterFactory
}