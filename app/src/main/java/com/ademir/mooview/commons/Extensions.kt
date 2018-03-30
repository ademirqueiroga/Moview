package com.ademir.mooview.commons

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.ademir.mooview.controllers.SessionController
import com.ademir.moview.model.User
import com.squareup.picasso.Picasso
import java.util.*

fun ViewGroup.inflate(@LayoutRes layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun ImageView.load(path: String, placeholder: Int) {
    if (path.isNotBlank()) {
        Picasso.get()
                .load(path)
                .placeholder(placeholder)
                .into(this)
    } else {
        setImageResource(placeholder)
    }
}

inline fun SharedPreferences.edit(func: SharedPreferences.Editor.() -> Unit) {
    val editor = edit()
    editor.func()
    editor.apply()
}

fun SharedPreferences.Editor.set(pair: Pair<String, String?>) =
        putString(pair.first, pair.second)!!

fun SharedPreferences.putUser(user: User) {
    edit {
        set(Constants.PREF_SESSION_TOKEN to user.token)
        set(Constants.PREF_SESSION_USERNAME to user.username)
        set(Constants.PREF_SESSION_NAME to user.name)
        set(Constants.PREF_SESSION_EMAIL to user.email)
        putInt(Constants.PREF_SESSION_FOLLOWING_COUNT, user.followingCount)
        putInt(Constants.PREF_SESSION_FOLLOWERS_COUNT, user.followingCount)
    }
}

fun SharedPreferences.loadUser(): Boolean {
    if (contains(Constants.PREF_SESSION_TOKEN)) {
        SessionController.user = User(this)
        return true
    } else {
        return false
    }
}

fun Context.getPreferences() = PreferenceManager.getDefaultSharedPreferences(this)!!

fun releaseDateFormatted(date: String?): String {
    date?.let {
        val split = date.split("-")
        val text = split[2] + '/' + split[1] + '/' + split[0]
        return text
    }
    return ""
}

fun <T : RecyclerView.ViewHolder> RecyclerView.prepare(adapter: RecyclerView.Adapter<T>,
                                                       layoutManager: RecyclerView.LayoutManager,
                                                       hasFixedSize: Boolean = true) {
    setAdapter(adapter)
    setLayoutManager(layoutManager)
    setHasFixedSize(hasFixedSize)
}

fun isToday(timeInMillis: Long): Boolean {
    val sCalendar = Calendar.getInstance()
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = timeInMillis
    return calendar.get(Calendar.YEAR) == sCalendar.get(Calendar.YEAR)
            && calendar.get(Calendar.MONTH) == sCalendar.get(Calendar.MONTH)
            && calendar.get(Calendar.DAY_OF_MONTH) == sCalendar.get(Calendar.DAY_OF_MONTH)
}