package com.ofal.ihsan.footballapp.view.activities.detail.match

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.google.gson.Gson
import com.ofal.ihsan.footballapp.R
import com.ofal.ihsan.footballapp.R.string.*
import com.ofal.ihsan.footballapp.api.ApiRequest
import com.ofal.ihsan.footballapp.model.Event
import com.ofal.ihsan.footballapp.model.Team
import com.ofal.ihsan.footballapp.model.db.MatchDB
import com.ofal.ihsan.footballapp.presenter.DetailMatchesPresenter
import com.ofal.ihsan.footballapp.utils.db
import com.ofal.ihsan.footballapp.utils.gone
import com.ofal.ihsan.footballapp.utils.visible
import com.ofal.ihsan.footballapp.view.basicui.DetailMatchesUI
import com.squareup.picasso.Picasso
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.support.v4.onRefresh
import java.text.SimpleDateFormat
import java.util.*

class DetailMatchesActivity : AppCompatActivity(), DetailMatchesView {

    private lateinit var eventDetail: Event
    private lateinit var badgeHome: Team
    private lateinit var badgeAway: Team

    private lateinit var tvDateEvent: TextView
    private lateinit var tvHomeScore: TextView
    private lateinit var tvAwayScore: TextView
    private lateinit var tvHomeTeam: TextView
    private lateinit var tvAwayTeam: TextView
    private lateinit var tvHomeFormation: TextView
    private lateinit var tvAwayFormation: TextView
    private lateinit var tvHomeShot: TextView
    private lateinit var tvAwayShot: TextView
    private lateinit var tvHomeGoalkeeper: TextView
    private lateinit var tvAwayGoalkeeper: TextView
    private lateinit var tvHomeDefense: TextView
    private lateinit var tvAwayDefense: TextView
    private lateinit var tvHomeMidfield: TextView
    private lateinit var tvAwayMidfield: TextView
    private lateinit var tvHomeForward: TextView
    private lateinit var tvAwayForward: TextView

    private lateinit var ivHomeBadge: ImageView
    private lateinit var ivAwayBadge: ImageView

    private lateinit var lyEventDetail: LinearLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout

    private var itemHomeId: String? = null
    private var itemAwayId: String? = null

    private lateinit var presenter: DetailMatchesPresenter
    private lateinit var idEventDetail: String

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DetailMatchesUI().setContentView(this)

        supportActionBar?.title = getString(match_detail)

        if (intent.extras != null) {
            idEventDetail = intent.getStringExtra(getString(item_eventdetail_id))
            itemHomeId = intent.getStringExtra(getString(item_home_id))
            itemAwayId = intent.getStringExtra(getString(item_away_id))
        }

        initId()
        getEventDetail()
    }

    private fun initId() {
        tvDateEvent = find(R.id.tvDateDetail)
        tvHomeScore = find(R.id.tvHomeScoreDetail)
        tvAwayScore = find(R.id.tvAwayScoreDetail)
        tvHomeTeam = find(R.id.tvHomeTeamDetail)
        tvAwayTeam = find(R.id.tvAwayTeamDetail)
        tvHomeFormation = find(R.id.tvHomeFormation)
        tvAwayFormation = find(R.id.tvAwayFormation)
        tvHomeShot = find(R.id.tvHomeShot)
        tvAwayShot = find(R.id.tvAwayShot)
        tvHomeGoalkeeper = find(R.id.tvHomeGoalKeeper)
        tvAwayGoalkeeper = find(R.id.tvAwayGoalKeeper)
        tvHomeDefense = find(R.id.tvHomeDefense)
        tvAwayDefense = find(R.id.tvAwayDefense)
        tvHomeMidfield = find(R.id.tvHomeMidfield)
        tvAwayMidfield = find(R.id.tvAwayMidfield)
        tvHomeForward = find(R.id.tvHomeForward)
        tvAwayForward = find(R.id.tvAwayForward)
        ivHomeBadge = find(R.id.ivHomeBadgeDetail)
        ivAwayBadge = find(R.id.ivAwayBadgeDetail)
        lyEventDetail = find(R.id.lyEventDetail)
        progressBar = find(R.id.pbDetailEvent)
        swipeRefresh = find(R.id.swipeRefreshDetail)
    }

    private fun getEventDetail() {
        favoriteState()
        presenter = DetailMatchesPresenter(this, ApiRequest(), Gson())
        presenter.getEventDetail(idEventDetail, itemHomeId, itemAwayId)

        swipeRefresh.onRefresh {
            presenter.getEventDetail(idEventDetail, itemHomeId, itemAwayId)
        }
    }

    private fun setPlayerTeam(playerName: String?): String? {
        val bulkPlayer = playerName?.split(";".toRegex())?.dropLastWhile {
            it.isEmpty()
        }?.map { it.trim() }?.toTypedArray()?.joinToString("\n")

        return bulkPlayer
    }

    private fun bindView() {

        swipeRefresh.isRefreshing = false

        val timeEvent = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                .parse(eventDetail.dateEvent)
        val dateEvent = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault())
                .format(timeEvent)

        tvDateEvent.text = dateEvent
        tvHomeScore.text = eventDetail.intHomeScore
        tvAwayScore.text = eventDetail.intAwayScore
        tvHomeTeam.text = eventDetail.strHomeTeam
        tvHomeFormation.text = eventDetail.strHomeFormation
        tvAwayFormation.text = eventDetail.strAwayFormation
        tvHomeShot.text = eventDetail.intHomeShots
        tvAwayShot.text = eventDetail.intAwayShots
        tvAwayTeam.text = eventDetail.strAwayTeam
        tvHomeGoalkeeper.text = setPlayerTeam(eventDetail.strHomeLineupGoalKeeper)
        tvAwayGoalkeeper.text = setPlayerTeam(eventDetail.strAwayLineupGoalkeeper)
        tvHomeDefense.text = setPlayerTeam(eventDetail.strHomeLineupDefense)
        tvAwayDefense.text = setPlayerTeam(eventDetail.strAwayLineupDefense)
        tvHomeMidfield.text = setPlayerTeam(eventDetail.strHomeLineupMidfield)
        tvAwayMidfield.text = setPlayerTeam(eventDetail.strAwayLineupMidfield)
        tvHomeForward.text = setPlayerTeam(eventDetail.strHomeLineupForward)
        tvAwayForward.text = setPlayerTeam(eventDetail.strAwayLineupForward)

        Picasso.get().load(badgeHome.teamBadge).into(ivHomeBadge)
        Picasso.get().load(badgeAway.teamBadge).into(ivAwayBadge)

    }

    override fun hideLoading() {
        progressBar.gone()
        lyEventDetail.visible()
    }

    override fun showLoading() {
        progressBar.visible()
        lyEventDetail.gone()
    }

    override fun showEventList(data: List<Event>, home: List<Team>, away: List<Team>) {
        eventDetail = data[0]
        badgeAway = away[0]
        badgeHome = home[0]

        bindView()
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
            val result = select(MatchDB.TABLE_MATCHES)
                    .whereArgs("(MATCHES_ID = {id})",
                            "id" to idEventDetail)
            val favorite = result.parseList(classParser<MatchDB>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun addToFavorite() {
        try {
            db.use {
                insert(MatchDB.TABLE_MATCHES,
                        MatchDB.MATCHES_ID to eventDetail.idEvent,
                        MatchDB.MATCHES_TIME to eventDetail.dateEvent,
                        MatchDB.HOME_TEAM to eventDetail.strHomeTeam,
                        MatchDB.HOME_SCORE to eventDetail.intHomeScore,
                        MatchDB.AWAY_TEAM to eventDetail.strAwayTeam,
                        MatchDB.AWAY_SCORE to eventDetail.intAwayScore,
                        MatchDB.HOME_TEAM_ID to itemHomeId,
                        MatchDB.AWAY_TEAM_ID to itemAwayId)
            }
            snackbar(swipeRefresh, "Added to Favorite").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(swipeRefresh, e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            db.use {
                delete(MatchDB.TABLE_MATCHES, "(MATCHES_ID = {id})",
                        "id" to idEventDetail)
            }
            snackbar(swipeRefresh, "Removed to Favorite").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(swipeRefresh, e.localizedMessage).show()
        }
    }

    private fun setFavorite() {
        menuItem?.getItem(0)?.icon =
                if(isFavorite)
                    ContextCompat.getDrawable(this,
                            R.drawable.ic_added_to_favorite)
                else
                    ContextCompat.getDrawable(this,
                            R.drawable.ic_add_to_favorite)
    }
}
