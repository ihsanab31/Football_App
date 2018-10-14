package com.ofal.ihsan.footballapp.view.fragment.favorite

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.ofal.ihsan.footballapp.R
import com.ofal.ihsan.footballapp.R.string.item_teamdetail_id
import com.ofal.ihsan.footballapp.adapter.FavoriteTeamsAdapter
import com.ofal.ihsan.footballapp.adapter.ViewPagerAdapter
import com.ofal.ihsan.footballapp.model.db.TeamDB
import com.ofal.ihsan.footballapp.utils.db
import com.ofal.ihsan.footballapp.utils.gone
import com.ofal.ihsan.footballapp.view.activities.detail.team.DetailTeamsActivity
import com.ofal.ihsan.footballapp.view.basicui.FavoriteTeamsUI
import com.ofal.ihsan.footballapp.view.fragment.favorite.match.FavoriteMatchesFragment
import com.ofal.ihsan.footballapp.view.fragment.favorite.team.FavoriteTeamsFragment
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.find
import org.jetbrains.anko.support.v4.onRefresh

class FavoriteFragment : Fragment() {

    private lateinit var mToolbar: Toolbar
    private lateinit var mTabLayout: TabLayout
    private lateinit var mViewPager: ViewPager

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)

        (activity as AppCompatActivity).setSupportActionBar(mToolbar)

        mToolbar.setTitleTextColor(resources.getColor(R.color.colorWhite))

        mTabLayout.setTabTextColors(resources.getColorStateList(R.color.colorWhite))
        mTabLayout.setSelectedTabIndicatorColor(resources.getColor(R.color.colorWhite))
        setupViewPager(mViewPager)
        mTabLayout.setupWithViewPager(mViewPager)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.view_pager_layout, container, false)

        mTabLayout = v.findViewById(R.id.tabs_layout)
        mToolbar = v.findViewById(R.id.toolbar_layout)
        mViewPager = v.findViewById(R.id.pager_layout)

        return v
    }

    companion object {
        fun favoritesInstance() : FavoriteFragment= FavoriteFragment()
    }

    private fun setupViewPager(pager: ViewPager) {
        val adapter = fragmentManager?.let { ViewPagerAdapter(it) }

        val matches = FavoriteMatchesFragment.favoriteMatchesInstance()
        adapter?.addFragment(matches, getString(R.string.matches))

        val teams = FavoriteTeamsFragment.favoriteTeamsInstance()
        adapter?.addFragment(teams, getString(R.string.teams))

        pager.adapter = adapter
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        menu?.clear()
        super.onPrepareOptionsMenu(menu)
    }
}
