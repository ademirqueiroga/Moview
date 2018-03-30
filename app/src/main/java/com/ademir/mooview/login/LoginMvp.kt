package com.ademir.mooview.login

import android.content.Context


interface LoginMvp {

    interface View {

        fun showProgressDialog(show: Boolean)
        fun showRegisterUI()
        fun showHomeUI()

    }

    interface Presenter {

        fun unsubscribe()

        fun login(context: Context, username: String, password: String)

    }

}
