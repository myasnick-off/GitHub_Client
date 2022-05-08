package com.example.githubclient.ui.users

import android.view.View
import com.example.githubclient.App
import com.example.githubclient.R
import com.example.githubclient.databinding.FragmentUsersBinding
import com.example.githubclient.model.GitHubUser
import com.example.githubclient.ui.GlideImageLoader
import com.example.githubclient.ui.details.DetailsFragment
import com.example.githubclient.viewBinding
import com.google.android.material.snackbar.Snackbar
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(R.layout.fragment_users), UsersView {

    private val presenter by moxyPresenter {
        App.appInstance.initUserSubComponent()
        App.appInstance.userSubComponent?.provideUsersPresenter()!!
    }

    private lateinit var adapter: UsersRecyclerAdapter
    private val binding: FragmentUsersBinding by viewBinding()


    override fun init() {
        adapter = UsersRecyclerAdapter(GlideImageLoader()) { user -> presenter.onUserClicked(user) }
        binding.usersRecyclerView.adapter = adapter
    }

    override fun updateList(users: List<GitHubUser>) {
        adapter.submitList(users)
    }

    override fun showProgress() {
        binding.usersProgressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.usersProgressBar.visibility = View.GONE
    }

    override fun showError(message: String?) {
        Snackbar
            .make(binding.root, "${getString(R.string.error)} ${message.orEmpty()}", Snackbar.LENGTH_SHORT)
            .show()
    }

    override fun navigateTo(user: GitHubUser) {
        parentFragmentManager.beginTransaction()
            .add(R.id.container, DetailsFragment.newInstance(user))
            .addToBackStack(null)
            .commit()
    }

    companion object {
        fun newInstance() = UsersFragment()
    }
}