package com.ofal.ihsan.footballapp.view.activities.detail.match

import com.ofal.ihsan.footballapp.model.Event
import com.ofal.ihsan.footballapp.model.Team

interface DetailMatchesView {
    fun hideLoading()
    fun showLoading()
    fun showEventList(data: List<Event>, home: List<Team>, away: List<Team>)
}