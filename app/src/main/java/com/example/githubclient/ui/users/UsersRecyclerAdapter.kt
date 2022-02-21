package com.example.githubclient.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.githubclient.databinding.ItemUsersListBinding
import com.example.githubclient.model.GitHubUser
import com.example.githubclient.ui.ImageLoader

class UsersRecyclerAdapter(
    private val imageLoader: ImageLoader<ImageView>,
    private val itemClickListener: (GitHubUser) -> Unit
) : ListAdapter<GitHubUser, UsersRecyclerAdapter.UsersViewHolder>(GithubUserItemCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val binding =
            ItemUsersListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UsersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.showUser(currentList[position])
    }

    inner class UsersViewHolder(private val binding: ItemUsersListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun showUser(gitHubUser: GitHubUser) {
            binding.loginTextView.text = gitHubUser.login
            gitHubUser.avatarUrl?.let { imageLoader.loadInto(it, binding.avatarImageView) }
            binding.root.setOnClickListener { itemClickListener(gitHubUser) }
        }
    }
}

object GithubUserItemCallBack : DiffUtil.ItemCallback<GitHubUser>() {

    override fun areItemsTheSame(oldItem: GitHubUser, newItem: GitHubUser): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: GitHubUser, newItem: GitHubUser): Boolean {
        return oldItem == newItem
    }

}