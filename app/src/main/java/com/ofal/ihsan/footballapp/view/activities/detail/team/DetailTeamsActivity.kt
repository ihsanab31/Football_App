package com.ofal.ihsan.footballapp.view.activities.detail.team

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.google.gson.Gson
import com.ofal.ihsan.footballapp.R
import com.ofal.ihsan.footballapp.adapter.DetailTeamsAdapter
import com.ofal.ihsan.footballapp.api.ApiRequest
import com.ofal.ihsan.footballapp.model.Player
import com.ofal.ihsan.footballapp.model.Team
import com.ofal.ihsan.footballapp.model.db.TeamDB
import com.ofal.ihsan.footballapp.presenter.DetailTeamsPresenter
import com.ofal.ihsan.footballapp.utils.db
import com.ofal.ihsan.footballapp.utils.gone
import com.ofal.ihsan.footballapp.utils.visible
import com.ofal.ihsan.footballapp.view.basicui.DetailTeamsUI
import com.squareup.picasso.Picasso
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.support.v4.onRefresh

class DetailTeamsActivity : AppCompatActivity(), DetailTeamsView {

    private lateinit var mTeams: Team
    private var mPlayer: MutableList<Player> = mutableListOf()

    private lateinit var ivBadgeTeamDetail: ImageView
    private lateinit var tvTitleDetailTeam: TextView
    private lateinit var tvYearDetailTeam: TextView
    private lateinit var tvStadiumDetailTeam: TextView
    private lateinit var tvDescriptionTeamDetail: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefreshTeamDetail: SwipeRefreshLayout
    private lateinit var lyTeamDetail: LinearLayout
    private lateinit var listPlayer: RecyclerView
    private lateinit var adapter: DetailTeamsAdapter

    private lateinit var presenter: DetailTeamsPresenter
    private lateinit var idTeamDetail: String
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DetailTeamsUI().setContentView(this)

        initId()

        if (intent.extras != null) {
            idTeamDetail = intent.getStringExtra(getString(R.string.item_teamdetail_id))
        }

        getTeamDetail()
    }

    private fun initId() {
        listPlayer = find(R.id.rvListPlayerTeam)
        ivBadgeTeamDetail = find(R.id.ivBadgeTeamDetail)
        tvTitleDetailTeam = find(R.id.tvTitleDetailTeam)
        tvYearDetailTeam = find(R.id.tvYearDetailTeam)
        tvStadiumDetailTeam = find(R.id.tvStadiumDetailTeam)
        tvDescriptionTeamDetail = find(R.id.tvDescriptionTeamDetail)
        lyTeamDetail = find(R.id.lyTeamDetail)
        swipeRefreshTeamDetail = find(R.id.swipeRefreshTeamDetail)
        progressBar = find(R.id.pbDetailTeam)
    }


    private fun getTeamDetail() {
        favoriteState()
        listPlayer.layoutManager = LinearLayoutManager(this)
        adapter = DetailTeamsAdapter(mPlayer)
        listPlayer.adapter = adapter

        presenter = DetailTeamsPresenter(this, ApiRequest(), Gson())
        presenter.getTeamDetail(idTeamDetail)

        swipeRefreshTeamDetail.onRefresh {
            presenter.getTeamDetail(idTeamDetail)
        }
    }

    private fun bindView() {

        supportActionBar?.title = mTeams.teamName
        Picasso.get().load(mTeams.teamBadge).into(ivBadgeTeamDetail)

        tvTitleDetailTeam.text = mTeams.teamName
        tvYearDetailTeam.text = mTeams.formedYear
        tvStadiumDetailTeam.text = mTeams.stadiumName
        tvDescriptionTeamDetail.text = mTeams.descriptionTeam
    }

    override fun hideLoading() {
        progressBar.gone()
        lyTeamDetail.visible()
    }

    override fun showLoading() {
        progressBar.visible()
        lyTeamDetail.gone()
    }

    override fun showEventList(data: List<Team>, player: List<Player>) {
        swipeRefreshTeamDetail.isRefreshing = false
        mPlayer.clear()
        mTeams = data[0]
        bindView()
        mPlayer.addAll(player)
        adapter.notifyDataSetChanged()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun favoriteState(){
        db.use {
            val result = select(TeamDB.TABLE_TEAMS)
                    .whereArgs("(TEAMS_ID = {id})",
                            "id" to idTeamDetail)
            val favorite = result.parseList(classParser<TeamDB>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun addToFavorite() {
        try {
            db.use {
                insert(TeamDB.TABLE_TEAMS,
                        TeamDB.TEAMS_ID to mTeams.teamId,
                        TeamDB.TEAMS_NAME to mTeams.teamName,
                        TeamDB.BADGE_TEAM to mTeams.teamBadge)
            }
            snackbar(swipeRefreshTeamDetail, "Added to Favorite").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(swipeRefreshTeamDetail, e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            db.use {
                delete(TeamDB.TABLE_TEAMS, "(TEAMS_ID = {id})",
                        "id" to idTeamDetail)
            }
            snackbar(swipeRefreshTeamDetail, "Removed to Favorite").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(swipeRefreshTeamDetail, e.localizedMessage).show()
        }
    }

    private fun setFavorite() {
        menuItem?.getItem(0)?.icon =
                if (isFavorite)
                    ContextCompat.getDrawable(this,
                            R.drawable.ic_added_to_favorite)
                else
                    ContextCompat.getDrawable(this,
                            R.drawable.ic_add_to_favorite)
    }
}
