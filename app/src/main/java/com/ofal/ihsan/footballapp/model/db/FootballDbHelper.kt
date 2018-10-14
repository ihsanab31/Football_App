package com.ofal.ihsan.footballapp.model.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 085749729115
 * on       : 19, September, 2018
 * ------------------------------
 * This class for
 */
class FootballDbHelper(ctx: Context): ManagedSQLiteOpenHelper(ctx,
        "FavoriteFootballApps.db", null, 1) {

    companion object {
        private var instance: FootballDbHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): FootballDbHelper {
            if (instance == null) {
                instance = FootballDbHelper(ctx.applicationContext)
            }
            return instance as FootballDbHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(MatchDB.TABLE_MATCHES, true,
                MatchDB.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                MatchDB.MATCHES_ID to TEXT + UNIQUE,
                MatchDB.MATCHES_TIME to TEXT,
                MatchDB.HOME_TEAM to TEXT,
                MatchDB.HOME_SCORE to TEXT,
                MatchDB.AWAY_TEAM to TEXT,
                MatchDB.AWAY_SCORE to TEXT,
                MatchDB.HOME_TEAM_ID to TEXT,
                MatchDB.AWAY_TEAM_ID to TEXT)

        db?.createTable(TeamDB.TABLE_TEAMS, true,
                TeamDB.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                TeamDB.TEAMS_ID to TEXT + UNIQUE,
                TeamDB.TEAMS_NAME to TEXT,
                TeamDB.BADGE_TEAM to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(MatchDB.TABLE_MATCHES, true)
        db?.dropTable(TeamDB.TEAMS_NAME, true)
    }
}