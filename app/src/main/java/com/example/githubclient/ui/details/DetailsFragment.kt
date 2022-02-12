package com.example.githubclient.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.githubclient.App
import com.example.githubclient.databinding.FragmentDetailsBinding
import com.example.githubclient.model.GitHubUser
import com.example.githubclient.ui.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class DetailsFragment : MvpAppCompatFragment(), DetailsView, BackButtonListener {

    private val presenter by moxyPresenter { DetailsPresenter(App.appInstance.router) }
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

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

    override fun setUserData() {
        arguments?.let {
            val user = it.getParcelable<GitHubUser>(KEY_USER)
            user?.let {
                binding.userLoginTextView.text = user.login
            }
        }
    }

    companion object {
        const val KEY_USER = "key_user"

        fun newInstance(bundle: Bundle): DetailsFragment {
            return DetailsFragment().apply {
                this.arguments = bundle
            }
        }
    }

    override fun backPressed() = presenter.backPressed()
}