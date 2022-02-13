package com.example.githubclient.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.githubclient.databinding.ItemReposListBinding
import com.example.githubclient.model.GitHubRepo

class DetailsRecyclerAdapter(
    private val itemClickListener: (GitHubRepo) -> Unit
) : ListAdapter<GitHubRepo, DetailsRecyclerAdapter.ReposViewHolder>(GithubRepoItemCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder {
        val binding =
            ItemReposListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReposViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
        holder.showRepos(currentList[position])
    }

    inner class ReposViewHolder(private val binding: ItemReposListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun showRepos(gitHubRepo: GitHubRepo) {
            binding.reposItemTextView.text = gitHubRepo.name
            binding.root.setOnClickListener { itemClickListener(gitHubRepo) }
        }
    }
}

object GithubRepoItemCallBack : DiffUtil.ItemCallback<GitHubRepo>() {

    override fun areItemsTheSame(oldItem: GitHubRepo, newItem: GitHubRepo): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: GitHubRepo, newItem: GitHubRepo): Boolean {
        return oldItem == newItem
    }

}