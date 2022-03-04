package com.example.githubclient.ui.repo

import android.annotation.SuppressLint
import androidx.core.os.bundleOf
import com.example.githubclient.App
import com.example.githubclient.R
import com.example.githubclient.databinding.FragmentRepoBinding
import com.example.githubclient.model.GitHubRepo
import com.example.githubclient.ui.BackButtonListener
import com.example.githubclient.viewBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepoFragment : MvpAppCompatFragment(R.layout.fragment_repo), RepoView, BackButtonListener {

    private val repo by lazy {
        requireArguments().getParcelable<GitHubRepo>(KEY_REPO)!!
    }

    private val presenter by moxyPresenter {
        App.appInstance.initRepoSubComponent()
        App.appInstance.repoSubComponent?.provideRepoPresenterFactory()?.presenter(repo)!!
    }

    private val binding: FragmentRepoBinding by viewBinding()


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