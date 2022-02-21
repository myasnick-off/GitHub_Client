package com.example.githubclient.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import androidx.core.content.getSystemService
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject

class NetworkStatus(context: Context) {
    private val connectivityManager = context.getSystemService<ConnectivityManager>()!!
    private val request = NetworkRequest.Builder().build()

    private val networkBehaviorSubject: BehaviorSubject<Boolean> = BehaviorSubject.create()

    init {
        networkBehaviorSubject.onNext(false)
        connectivityManager.registerNetworkCallback(request, object : ConnectivityManager.NetworkCallback(){
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                networkBehaviorSubject.onNext(true)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                networkBehaviorSubject.onNext(false)
            }

            override fun onUnavailable() {
                super.onUnavailable()
                networkBehaviorSubject.onNext(false)
            }
        })
    }

    fun isOnline() = networkBehaviorSubject.value ?: false

    fun isOnlineSingle(): Single<Boolean> = networkBehaviorSubject.first(true)
}