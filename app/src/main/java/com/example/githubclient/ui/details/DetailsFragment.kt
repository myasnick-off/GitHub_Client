package com.example.githubclient.ui.details

import android.view.View
import androidx.core.os.bundleOf
import com.example.githubclient.App
import com.example.githubclient.R
import com.example.githubclient.databinding.FragmentDetailsBinding
import com.example.githubclient.model.GitHubRepo
import com.example.githubclient.model.GitHubUser
import com.example.githubclient.ui.GlideImageLoader
import com.example.githubclient.ui.repo.RepoFragment
import com.example.githubclient.viewBinding
import com.google.android.material.snackbar.Snackbar
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class DetailsFragment : MvpAppCompatFragment(R.layout.fragment_details), DetailsView {

    private val user by lazy {
        requireArguments().getParcelable<GitHubUser>(KEY_USER)!!
    }
    private val presenter by moxyPresenter {
        App.appInstance.initRepoSubComponent()
        App.appInstance.repoSubComponent?.provideDetailsPresenterFactory()?.presenter(user)!!
    }

    private val binding: FragmentDetailsBinding by viewBinding()

    private val adapter by lazy {
        DetailsRecyclerAdapter() { repo -> presenter.onItemClicked(repo) }
    }
    private val imageLoader by lazy { GlideImageLoader() }


    override fun initUserData(user: GitHubUser) {
        binding.userLoginTextView.text = user.login
        user.avatarUrl?.let { url -> imageLoader.loadInto(url, binding.photoImageView) }
    }

    override fun initRepoList() {
        binding.reposRecyclerView.adapter = adapter
    }

    override fun updateRepoList(repos: List<GitHubRepo>) {
        adapter.submitList(repos)
    }

    override fun showProgress() {
        binding.detailsProgressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.detailsProgressBar.visibility = View.GONE
    }

    override fun showError(message: String?) {
        Snackbar
            .make(binding.root, "${getString(R.string.error)} ${message.orEmpty()}", Snackbar.LENGTH_SHORT)
            .show()
    }

    override fun navigateTo(repo: GitHubRepo) {
        parentFragmentManager.beginTransaction()
            .add(R.id.container, RepoFragment.newInstance(repo))
            .addToBackStack(null)
            .commit()
    }

    companion object {
        const val KEY_USER = "key_user"

        fun newInstance(user: GitHubUser): DetailsFragment {
            return DetailsFragment().apply {
                this.arguments = bundleOf(KEY_USER to user)
            }
        }
    }
}