package com.ofal.ihsan.footballapp.view.activities.detail.player

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.google.gson.Gson
import com.ofal.ihsan.footballapp.R
import com.ofal.ihsan.footballapp.api.ApiRequest
import com.ofal.ihsan.footballapp.api.TheSportApi
import com.ofal.ihsan.footballapp.model.Player
import com.ofal.ihsan.footballapp.presenter.DetailPlayersPresenter
import com.ofal.ihsan.footballapp.utils.gone
import com.ofal.ihsan.footballapp.utils.visible
import com.ofal.ihsan.footballapp.view.basicui.DetailPlayersUI
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.support.v4.onRefresh

class DetailPlayersActivity : AppCompatActivity(), DetailPlayerView {

    private lateinit var mPlayer : Player

    private lateinit var ivBgPlayerDetail : ImageView
    private lateinit var lyPlayerDetail: LinearLayout
    private lateinit var tvWeightPlayer: TextView
    private lateinit var tvHeightPlayer: TextView
    private lateinit var tvForwardPlayer: TextView
    private lateinit var tvDescriptionPlayerDetail: TextView
    private lateinit var swipeRefreshPlayerDetail: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: DetailPlayersPresenter

    private lateinit var idPlayerDetail: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DetailPlayersUI().setContentView(this)

        initId()

        if (intent.extras != null) {
            idPlayerDetail = intent.getStringExtra(getString(R.string.item_teamplayer_id))
        }

        getPlayerDetail()
    }

    private fun initId() {
        ivBgPlayerDetail = find(R.id.bgPlayerDetail)
        lyPlayerDetail = find(R.id.lyPlayerDetail)
        tvWeightPlayer = find(R.id.tvWeightPlayer)
        tvHeightPlayer = find(R.id.tvHeightPlayer)
        tvForwardPlayer = find(R.id.tvForwardPlayer)
        tvDescriptionPlayerDetail = find(R.id.tvDescriptionPlayerDetail)
        swipeRefreshPlayerDetail = find(R.id.swipeRefreshPlayerDetail)
        progressBar = find(R.id.pbDetailPlayer)

    }

    private fun getPlayerDetail() {
        presenter = DetailPlayersPresenter(this, ApiRequest(), Gson())
        presenter.getPlayerDetail(idPlayerDetail)

        swipeRefreshPlayerDetail.onRefresh {
            presenter.getPlayerDetail(idPlayerDetail)
        }

        Log.d("cekurl", TheSportApi.getDetailPlayerTeam(idPlayerDetail))
    }

    private fun bindView() {
        supportActionBar?.title = mPlayer.strPlayer
        Picasso.get().load(mPlayer.strFanart1).into(ivBgPlayerDetail)
        tvWeightPlayer.text = mPlayer.strWeight
        tvHeightPlayer.text = mPlayer.strHeight
        tvForwardPlayer.text = mPlayer.strPosition
        tvDescriptionPlayerDetail.text = mPlayer.strDescription
    }

    override fun hideLoading() {
        progressBar.gone()
        lyPlayerDetail.visible()
    }

    override fun showLoading() {
        progressBar.visible()
        lyPlayerDetail.gone()
    }

    override fun showPlayerDetail(player: List<Player>) {
        swipeRefreshPlayerDetail.isRefreshing = false
        mPlayer = player[0]
        bindView()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> {
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
