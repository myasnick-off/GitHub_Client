package com.example.githubclient

import com.example.githubclient.model.GitHubUser
import com.example.githubclient.repository.users.UsersRepository
import com.example.githubclient.ui.users.UsersPresenter
import com.example.githubclient.ui.users.UsersView
import com.nhaarman.mockito_kotlin.atLeast
import com.nhaarman.mockito_kotlin.atMost
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.lang.RuntimeException

class UsersPresenterTest {

    private lateinit var presenter: UsersPresenter

    @Mock
    private lateinit var repository: UsersRepository

    @Mock
    private lateinit var view: UsersView

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = UsersPresenter(repository, view)

        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun usersPresenterTest_Repository_GetUsers_Method_Verifying() {
        Mockito.`when`(repository.getUsers()).thenReturn(Single.just(emptyList()))
        presenter.loadData()
        verify(repository, atMost(1)).getUsers()
    }

    @Test
    fun usersPresenterTest_View_UpdateList_Method_Verifying() {
        Mockito.`when`(repository.getUsers()).thenReturn(Single.just(emptyList()))
        presenter.loadData()
        verify(view, times(1)).updateList(emptyList())
    }

    @Test
    fun usersPresenterTest_View_ShowError_Method_Verifying() {
        Mockito.`when`(repository.getUsers()).thenReturn(Single.error(Throwable("Some Error!")))
        presenter.loadData()
        verify(view, atLeast(1)).showError("Some Error!")
    }

    @Test
    fun usersPresenterTest_View_ShowProgress_Method_Verifying() {
        Mockito.`when`(repository.getUsers()).thenReturn(Single.just(emptyList()))
        presenter.loadData()
        verify(view, times(1)).showProgress()
    }

    @Test
    fun usersPresenterTest_View_HideProgress_Method_Verifying() {
        Mockito.`when`(repository.getUsers()).thenReturn(Single.just(emptyList()))
        presenter.loadData()
        verify(view, times(1)).hideProgress()
    }

    @Test
    fun usersPresenterTest_View_NavigateTo_Method_Verifying() {
        val user = GitHubUser(id = 0, login = "User", avatarUrl = null, reposUrl = "url")
        presenter.onUserClicked(user)
        verify(view, times(1)).navigateTo(user)
    }

}