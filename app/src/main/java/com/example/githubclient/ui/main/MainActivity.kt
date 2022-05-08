package com.example.githubclient.ui.main

import android.os.Bundle
import com.example.githubclient.App
import com.example.githubclient.R
import com.example.githubclient.databinding.ActivityMainBinding
import com.example.githubclient.ui.users.UsersFragment
import moxy.MvpAppCompatActivity

class MainActivity : MvpAppCompatActivity(), MainView {


    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appInstance.appComponent.inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        if (savedInstanceState == null) {
            navigateTo()
        }
    }

    override fun navigateTo() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, UsersFragment.newInstance())
            .commit()
    }
}