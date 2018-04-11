package com.ademir.moview.commons


class Constants {


    object Urls {

        //TMDB
        const val TMDB_DOMAIN = "https://api.themoviedb.org/3/"
        const val MOVIE = TMDB_DOMAIN + "movie/"
        const val POPULAR_MOVIES = MOVIE + "popular/"

        const val TMDB_POSTER = "https://image.tmdb.org/t/p/w185"
        const val TMDB_BACKDROP = "https://image.tmdb.org/t/p/w500"
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

