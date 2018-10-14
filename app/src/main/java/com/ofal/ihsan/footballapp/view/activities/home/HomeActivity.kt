package com.ofal.ihsan.footballapp.view.activities.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import com.ofal.ihsan.footballapp.R
import com.ofal.ihsan.footballapp.view.basicui.HomeActivityUI
import com.ofal.ihsan.footballapp.view.fragment.favorite.FavoriteFragment
import com.ofal.ihsan.footballapp.view.fragment.match.MatchesFragment
import com.ofal.ihsan.footballapp.view.fragment.teams.TeamsFragment
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView

class HomeActivity : AppCompatActivity() {
    lateinit var toolBar: ActionBar
    lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        HomeActivityUI().setContentView(this)

        bottomNavigation = find(R.id.navigation)

        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        if (savedInstanceState == null) {
            addFragment(MatchesFragment.matchesInstance())
        }
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_matches -> {
                val matchesFragment = MatchesFragment.matchesInstance()
                addFragment(matchesFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_teams -> {
                val teamsFragment = TeamsFragment.teamsInstance()
                addFragment(teamsFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favorites -> {
                val favoFragment = FavoriteFragment.favoritesInstance()
                addFragment(favoFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun addFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }
}
