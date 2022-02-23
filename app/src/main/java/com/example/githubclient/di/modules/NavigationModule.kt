package com.example.githubclient.di.modules

import com.example.githubclient.ui.AppScreens
import com.example.githubclient.ui.IScreens
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides

@Module
class NavigationModule {

    @Provides
    fun provideCicerone(): Cicerone<Router> {
        return Cicerone.create()
    }

    @Provides
    fun provideNavigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder {
        return cicerone.getNavigatorHolder()
    }

    @Provides
    fun provideRouter(cicerone: Cicerone<Router>) : Router {
        return cicerone.router
    }

    @Provides
    fun provideAppScreens() : IScreens {
        return AppScreens()
    }
}