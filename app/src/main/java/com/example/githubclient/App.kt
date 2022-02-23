package com.example.githubclient

import android.app.Application
import com.example.githubclient.di.component.DaggerAppComponent
import com.example.githubclient.di.modules.ContextModule

class App : Application() {

    val appComponent by lazy {
        DaggerAppComponent.builder()
            .contextModule(ContextModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        lateinit var appInstance: App
    }
}