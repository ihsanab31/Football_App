package com.ofal.ihsan.footballapp.view.fragment.teams

import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.MenuItemCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.*
import android.widget.*
import com.google.gson.Gson
import com.ofal.ihsan.footballapp.R
import com.ofal.ihsan.footballapp.R.array.league_array
import com.ofal.ihsan.footballapp.R.color.colorAccent
import com.ofal.ihsan.footballapp.adapter.TeamsAdapter
import com.ofal.ihsan.footballapp.api.ApiRequest
import com.ofal.ihsan.footballapp.model.Team
import com.ofal.ihsan.footballapp.presenter.TeamsPresenter
import com.ofal.ihsan.footballapp.utils.gone
import com.ofal.ihsan.footballapp.utils.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.*


class TeamsFragment : Fragment(), TeamsView {

    private var teams:MutableList<Team> = mutableListOf()
    private lateinit var lisTeam: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner
    private lateinit var presenter: TeamsPresenter
    private lateinit var adapter: TeamsAdapter
    private lateinit var leagueName: String

    private lateinit var mToolbar: Toolbar

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)

        (activity as AppCompatActivity).setSupportActionBar(mToolbar)
        mToolbar.setTitleTextColor(resources.getColor(R.color.colorWhite))
        mToolbar.setBackgroundColor(resources.getColor(R.color.colorPrimary))
        initAdapter()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        return UI {

            linearLayout {
                lparams(width = matchParent, height = matchParent)
                orientation = LinearLayout.VERTICAL
                appBarLayout {
                    id = R.id.appBarTeams
                    lparams(width = matchParent, height = wrapContent)

                    mToolbar = toolbar {
                        lparams(width = matchParent, height = dimenAttr(android.support.design.R.attr.actionBarSize))
                        id = R.id.toolbar_teams
                    }
                }

                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    orientation = LinearLayout.VERTICAL

                    topPadding = dip(16)
                    leftPadding = dip(16)
                    rightPadding = dip(16)

                    spinner = spinner()

                    swipeRefresh = swipeRefreshLayout {
                        setColorSchemeResources(colorAccent,
                                android.R.color.holo_green_light,
                                android.R.color.holo_orange_light,
                                android.R.color.holo_red_light)

                        relativeLayout {
                            lparams(width = matchParent, height = wrapContent)

                            lisTeam = recyclerView {
                                id = R.id.rvListTeams
                                lparams(width = matchParent, height = wrapContent)
                                layoutManager = LinearLayoutManager(ctx)
                            }

                            progressBar = progressBar {
                            }.lparams {
                                centerHorizontally()
                            }
                        }
                    }
                }
            }
        }.view

        mToolbar = find(R.id.toolbar_teams)
    }

    companion object {
        fun teamsInstance(): TeamsFragment = TeamsFragment()
    }

    private fun initAdapter() {
        val spinnerItems = resources.getStringArray(league_array)
        val spinnerAdapter = ArrayAdapter(ctx,
                android.R.layout.simple_spinner_dropdown_item, spinnerItems)

        spinner.adapter = spinnerAdapter

        adapter = TeamsAdapter(teams)
        lisTeam.adapter = adapter

        presenter = TeamsPresenter(this, ApiRequest(), Gson())

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                leagueName = spinner.selectedItem.toString()
                if(Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                    leagueName = leagueName.replace(" ", "%20")
                }
                presenter.getTeamList(leagueName)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        swipeRefresh.onRefresh {
            presenter.getTeamList(leagueName)
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.gone()
    }

    override fun showEventList(data: List<Team>?) {
        swipeRefresh.isRefreshing = false
        teams.clear()
        if(data != null) teams.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        menu?.clear()
        inflater?.inflate(R.menu.search_menu, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = MenuItemCompat.getActionView(searchItem) as SearchView
        searchView.backgroundColorResource = R.color.colorWhite
        searchView.setQueryHint(getString(R.string.example_search_team))
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter.getSearchTeam(query)
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                presenter.getSearchTeam(query)
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }
}
