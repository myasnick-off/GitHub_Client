package com.example.githubclient

import android.app.Application
import com.example.githubclient.di.component.DaggerAppComponent
import com.example.githubclient.di.component.RepoSubComponent
import com.example.githubclient.di.component.UserSubComponent
import com.example.githubclient.di.modules.ContextModule

class App : Application() {

    val appComponent by lazy {
        DaggerAppComponent.builder()
            .contextModule(ContextModule(this))
            .build()
    }

    var userSubComponent: UserSubComponent? = null
    private set
    var repoSubComponent: RepoSubComponent? = null
    private set

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    fun initUserSubComponent(): UserSubComponent {
        userSubComponent = appComponent.provideUserSubcomponent()
        return userSubComponent!!
    }

    fun initRepoSubComponent(): RepoSubComponent {
        repoSubComponent = appComponent.provideUserSubcomponent().provideRepoSubComponent()
        return repoSubComponent!!
    }

    fun destroyUserScope() {
        userSubComponent = null
    }

    fun destroyRepoScope() {
        repoSubComponent = null
    }

    companion object {
        lateinit var appInstance: App
    }
}