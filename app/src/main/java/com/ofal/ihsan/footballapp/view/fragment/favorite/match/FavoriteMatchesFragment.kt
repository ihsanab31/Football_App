package com.ofal.ihsan.footballapp.view.fragment.favorite.match

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.ofal.ihsan.footballapp.R
import com.ofal.ihsan.footballapp.R.string.*
import com.ofal.ihsan.footballapp.adapter.FavoriteMatchesAdapter
import com.ofal.ihsan.footballapp.model.db.MatchDB
import com.ofal.ihsan.footballapp.utils.db
import com.ofal.ihsan.footballapp.utils.gone
import com.ofal.ihsan.footballapp.view.activities.detail.match.DetailMatchesActivity
import com.ofal.ihsan.footballapp.view.basicui.FavoriteMatchesUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.find
import org.jetbrains.anko.support.v4.onRefresh


class FavoriteMatchesFragment : Fragment() {

    private var favoMatch: MutableList<MatchDB> = mutableListOf()
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: FavoriteMatchesAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initId()

        adapter = FavoriteMatchesAdapter(favoMatch) {
            ctx.startActivity<DetailMatchesActivity>(getString(item_eventdetail_id)
                    to "${it.eventId}",
                    getString(item_home_id) to "${it.homeTeamId}",
                    getString(item_away_id) to "${it.awayTeamId}")
        }

        recyclerView.adapter = adapter
        showFavorite()
        swipeRefreshLayout.onRefresh {
            favoMatch.clear()
            showFavorite()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return FavoriteMatchesUI().createView(AnkoContext.create(ctx, this))
    }

    companion object {
        fun favoriteMatchesInstance(): FavoriteMatchesFragment = FavoriteMatchesFragment()
    }

    private fun initId() {
        swipeRefreshLayout = find(R.id.swipeRefreshFavoMatch)
        recyclerView = find(R.id.rvFavoMatch)
        progressBar = find(R.id.pbFavoMatches)
    }

    private fun showFavorite() {
        context?.db?.use {
            swipeRefreshLayout.isRefreshing = false
            progressBar.gone()
            val result = select(MatchDB.TABLE_MATCHES)
            val favorite = result.parseList(classParser<MatchDB>())
            favoMatch.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }
}
