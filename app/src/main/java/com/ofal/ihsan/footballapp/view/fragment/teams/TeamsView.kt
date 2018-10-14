package com.ofal.ihsan.footballapp.view.fragment.teams

import com.ofal.ihsan.footballapp.model.Team


interface TeamsView {
    fun showLoading()
    fun hideLoading()
    fun showEventList(data: List<Team>?)
}