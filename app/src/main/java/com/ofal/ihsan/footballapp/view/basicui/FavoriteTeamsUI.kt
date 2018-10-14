package com.ofal.ihsan.footballapp.view.basicui

import android.support.v7.widget.LinearLayoutManager
import com.ofal.ihsan.footballapp.R
import com.ofal.ihsan.footballapp.utils.SpaceItemDecoration
import com.ofal.ihsan.footballapp.view.fragment.favorite.team.FavoriteTeamsFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 085749729115
 * on       : 19, September, 2018
 * ------------------------------
 * This class for basic ui favorite team
 */
class FavoriteTeamsUI : AnkoComponent<FavoriteTeamsFragment> {

    override fun createView(ui: AnkoContext<FavoriteTeamsFragment>) = with(ui) {
        relativeLayout {
            lparams(width = matchParent, height = matchParent)

            swipeRefreshLayout {
                id = R.id.swipeRefreshFavoTeams
                setColorSchemeResources(R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                recyclerView {
                    lparams(width = matchParent, height = wrapContent)
                    id = R.id.rvFavoTeams
                    layoutManager = LinearLayoutManager(ctx)
                    addItemDecoration(SpaceItemDecoration(8))
                }
            }

            progressBar {
                id = R.id.pbFavoTeams
            }.lparams {
                centerHorizontally()
            }
        }
    }
}