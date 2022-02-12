package com.example.githubclient.ui.details

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.AddToEndSingle

interface DetailsView : MvpView {

    @AddToEndSingle
    fun setUserData()
}