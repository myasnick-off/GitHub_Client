package com.example.githubclient

import android.os.Bundle
import com.example.githubclient.databinding.ActivityMainBinding
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    private val presenter by moxyPresenter { MainPresenter(UsersRepository()) }
    private lateinit var adapter: UsersRecyclerAdapter
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }

    override fun init() {
        adapter = UsersRecyclerAdapter(presenter.usersListPresenter)
        binding?.let {
            it.usersRecyclerView.adapter = adapter
        }
    }

    override fun updateList() {
        adapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}