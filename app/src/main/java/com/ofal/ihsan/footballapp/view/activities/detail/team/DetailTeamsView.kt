package com.ofal.ihsan.footballapp.view.activities.detail.team

import com.ofal.ihsan.footballapp.model.Player
import com.ofal.ihsan.footballapp.model.Team

interface DetailTeamsView {
    fun hideLoading()
    fun showLoading()
    fun showEventList(data: List<Team>, player: List<Player>)
}