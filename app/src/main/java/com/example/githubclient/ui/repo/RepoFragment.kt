package com.example.githubclient.ui.repo

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.githubclient.App
import com.example.githubclient.R
import com.example.githubclient.databinding.FragmentRepoBinding
import com.example.githubclient.model.GitHubRepo
import com.example.githubclient.ui.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepoFragment : MvpAppCompatFragment(), RepoView, BackButtonListener {

    private val repo by lazy {
        requireArguments().getParcelable<GitHubRepo>(KEY_REPO)
    }

    private val presenter by moxyPresenter {
        RepoPresenter(repo!!, App.appInstance.router)
    }

    private var _binding: FragmentRepoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepoBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun init(repo: GitHubRepo) = with(binding) {
        nameTextView.text = repo.name
        langTextView.text = repo.language.orEmpty()
        updateTextView.text = repo.updatedAt
        starsTextView.text = "${repo.stargazersCount} ${getString(R.string.stars)}"
        watchersTextView.text = "${repo.watchersCount} ${getString(R.string.watchers)}"
        forkTextView.text = "${repo.forksCount} ${getString(R.string.forks)}"
        aboutTextView.text = repo.description.orEmpty()
    }

    override fun backPressed() = presenter.backPressed()

    companion object {
        const val KEY_REPO = "key_repo"

        fun newInstance(repo: GitHubRepo): RepoFragment {
            return RepoFragment().apply {
                this.arguments = bundleOf(KEY_REPO to repo)
            }
        }
    }
}