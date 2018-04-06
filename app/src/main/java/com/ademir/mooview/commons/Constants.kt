package com.ademir.mooview.commons


class Constants {


    object Urls {

        const val DOMAIN = "https://mooview.herokuapp.com/"
//        const val DOMAIN = "http://192.168.1.100/"

        const val AUTH = DOMAIN + "auth/"
        const val SIGN_UP = DOMAIN + "signup/"

        //users
        const val USERS = DOMAIN + "users/"
        const val USER_COMMENTS = USERS + "comments/"
        const val FOLLOW = USERS + "relationships/"
        const val FAVORIRES = USERS + "favorites/"
        const val WATCHLIST = USERS + "watchlist/"
        const val USER_TIMELINE = USERS + "timeline/"

        //core
        const val SEARCH = DOMAIN + "search/"
        const val FEED = DOMAIN + "feed/"

        //movies
        const val MOVIES = DOMAIN + "movies/"
        const val MOVIE_DETAILS = MOVIES + "details/{id}/"
        const val MOVIE_COMMENTS = MOVIES + "comments/"


        //TMDB
        const val TMDB_POSTER = "https://image.tmdb.org/t/p/w154"
        const val TMDB_BACKDROP = "https://image.tmdb.org/t/p/w300"
    }

    companion object {

        val EXTRA_MOVIE_ID = "extra_movie_id"

        //prefs
        const val PREF_SESSION_USERNAME = "pref_username"
        const val PREF_SESSION_NAME = "pref_name"
        const val PREF_SESSION_EMAIL = "pref_email"
        const val PREF_SESSION_TOKEN = "pref_token"
        const val PREF_SESSION_FOLLOWING_COUNT = "pref_following_count"
        const val PREF_SESSION_FOLLOWERS_COUNT = "pref_followers_count"

    }
}

