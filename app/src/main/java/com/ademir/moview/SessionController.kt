package com.ademir.moview

import android.content.Context
import com.ademir.moview.commons.getPreferences
import com.ademir.moview.commons.loadUser
import com.ademir.moview.commons.putUser
import com.ademir.moview.model.User
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Observable


object SessionController {

    var user: User? = null

    fun loadSession(context: Context) =
            Observable.fromCallable {
//                context.getPreferences().loadUser()
                FirebaseAuth.getInstance().currentUser != null
            }!!

    fun createSession(context: Context, user: User) =
        Observable.fromCallable { context.getPreferences().putUser(user) }.subscribe()!!

    fun clearSession(context: Context) = context.getPreferences().edit().clear().apply()

}
