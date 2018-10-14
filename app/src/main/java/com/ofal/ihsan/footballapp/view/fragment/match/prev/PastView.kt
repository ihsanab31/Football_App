package com.ofal.ihsan.footballapp.view.fragment.match.prev

import com.ofal.ihsan.footballapp.model.Event


interface PastView {
    fun showLoading()
    fun hideLoading()
    fun showEventList(data: List<Event>?)
    fun errorMessage(message: String?)
}