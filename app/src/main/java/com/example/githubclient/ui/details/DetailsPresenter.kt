package com.example.githubclient.ui.details

import androidx.core.graphics.scaleMatrix
import com.example.githubclient.model.GitHubRepo
import com.example.githubclient.model.GitHubUser
import com.example.githubclient.repository.repos.ReposRepository
import com.example.githubclient.ui.IScreens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class DetailsPresenter(
    private val user: GitHubUser,
    private val repository: ReposRepository,
    val router: Router,
    val screens: IScreens
) :
    MvpPresenter<DetailsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initUserData(user)
        viewState.initRepoList()

        repository.getRepos(user.reposUrl)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    viewState.updateRepoList(it)
                },
                {
                    viewState.showError(it.message)
                }
            )
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    fun onItemClicked(repo: GitHubRepo) {
        router.navigateTo(screens.repoScreen(repo))
    }
}