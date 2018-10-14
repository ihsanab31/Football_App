package com.ofal.ihsan.footballapp.view.fragment.match.next

import com.ofal.ihsan.footballapp.model.Event


interface NextView {
    fun showLoading()
    fun hideLoading()
    fun showEventList(data: List<Event>?)
    fun errorMessage(message: String?)
}