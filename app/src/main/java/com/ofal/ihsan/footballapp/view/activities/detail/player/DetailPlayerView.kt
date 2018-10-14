package com.ofal.ihsan.footballapp.view.activities.detail.player

import com.ofal.ihsan.footballapp.model.Player

interface DetailPlayerView {
    fun hideLoading()
    fun showLoading()
    fun showPlayerDetail(player: List<Player>)
}