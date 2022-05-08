package com.example.githubclient.di.component

import com.example.githubclient.di.modules.ContextModule
import com.example.githubclient.di.modules.DatabaseModule
import com.example.githubclient.di.modules.NetworkModule
import com.example.githubclient.ui.main.MainActivity
import dagger.Component
import javax.inject.Singleton


@Component(
    modules = [
        ContextModule::class,
        NetworkModule::class,
        DatabaseModule::class
    ]
)

@Singleton
interface AppComponent {

    fun provideUserSubcomponent(): UserSubComponent

    fun inject(mainActivity: MainActivity)
}