package com.example.githubclient.ui

import android.os.Bundle
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun details(bundle: Bundle): Screen
}