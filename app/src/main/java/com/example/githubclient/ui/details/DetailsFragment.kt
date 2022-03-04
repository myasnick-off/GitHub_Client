package com.example.githubclient.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.githubclient.App
import com.example.githubclient.R
import com.example.githubclient.databinding.FragmentDetailsBinding
import com.example.githubclient.model.GitHubRepo
import com.example.githubclient.model.GitHubUser
import com.example.githubclient.ui.BackButtonListener
import com.example.githubclient.ui.GlideImageLoader
import com.google.android.material.snackbar.Snackbar
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class DetailsFragment : MvpAppCompatFragment(), DetailsView, BackButtonListener {

    private val user by lazy {
        requireArguments().getParcelable<GitHubUser>(KEY_USER)!!
    }
    private val presenter by moxyPresenter {
        App.appInstance.appComponent.provideDetailsPresenterFactory().presenter(user)
    }

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy {
        DetailsRecyclerAdapter() { repo -> presenter.onItemClicked(repo) }
    }
    private val imageLoader by lazy { GlideImageLoader() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

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

    override fun backPressed() = presenter.backPressed()

    companion object {
        const val KEY_USER = "key_user"

        fun newInstance(user: GitHubUser): DetailsFragment {
            return DetailsFragment().apply {
                this.arguments = bundleOf(KEY_USER to user)
            }
        }
    }
}