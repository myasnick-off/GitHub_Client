package com.example.githubclient

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubclient.databinding.ItemUsersListBinding

class UsersRecyclerAdapter(val presenter: IUserListPresenter) :
    RecyclerView.Adapter<UsersRecyclerAdapter.UsersViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UsersRecyclerAdapter.UsersViewHolder {
        val binding =
            ItemUsersListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = UsersViewHolder(binding).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }

    override fun getItemCount() = presenter.getCount()

    inner class UsersViewHolder(private val binding: ItemUsersListBinding) :
        RecyclerView.ViewHolder(binding.root), UserItemView {

        override var pos = -1

        override fun setLogin(text: String) {
            binding.loginTextView.text = text
        }
    }
}