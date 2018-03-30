package com.ademir.moview.model

import android.content.SharedPreferences
import com.ademir.mooview.commons.Constants.Companion.PREF_SESSION_EMAIL
import com.ademir.mooview.commons.Constants.Companion.PREF_SESSION_FOLLOWERS_COUNT
import com.ademir.mooview.commons.Constants.Companion.PREF_SESSION_FOLLOWING_COUNT
import com.ademir.mooview.commons.Constants.Companion.PREF_SESSION_NAME
import com.ademir.mooview.commons.Constants.Companion.PREF_SESSION_TOKEN
import com.ademir.mooview.commons.Constants.Companion.PREF_SESSION_USERNAME
import com.ademir.mooview.model.Movie
import com.squareup.moshi.Json


class User(pref: SharedPreferences) {

    var id: Int = 0

    var username: String? = null
    var name: String? = null
    var email: String? = null
    var profile_picture: String? = null

    var wishlist: List<Movie>? = null
    var followers: List<User>? = null
    var following: List<User>? = null

    @Json(name = "followers_count")
    var followersCount: Int = 0

    @Json(name = "following_count")
    var followingCount: Int = 0

    @Json(name = "followed_by_me")
    var followedByMe: Boolean = false

    var token: String = ""

    init {
        with(pref) {
            name = getString(PREF_SESSION_NAME, "")
            username = getString(PREF_SESSION_USERNAME, "")
            email = getString(PREF_SESSION_EMAIL, "")
            token = getString(PREF_SESSION_TOKEN, "")
            followingCount = getInt(PREF_SESSION_FOLLOWING_COUNT, -1)
            followersCount = getInt(PREF_SESSION_FOLLOWERS_COUNT, -1)
        }
    }

}
