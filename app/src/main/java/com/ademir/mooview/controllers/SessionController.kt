package com.ademir.mooview.controllers

import android.content.Context
import com.ademir.mooview.commons.getPreferences
import com.ademir.mooview.commons.loadUser
import com.ademir.mooview.commons.putUser
import com.ademir.moview.model.User
import io.reactivex.Observable


object SessionController {

    var user: User? = null

    fun loadSession(context: Context) =
            Observable.fromCallable { context.getPreferences().loadUser() }!!

    fun createSession(context: Context, user: User) =
        Observable.fromCallable { context.getPreferences().putUser(user) }.subscribe()!!

    fun clearSession(context: Context) = context.getPreferences().edit().clear().apply()

}
