package com.ademir.moview.ui.home.profile

import com.google.firebase.auth.FirebaseUser

/**
 * Created by ademir on 05/04/18.
 */
interface ProfileContract {

    interface View {

        fun setUser(user: FirebaseUser?)

        fun showSignInUi()

    }

    interface Presenter {

        fun bindView(view: View)

        fun loadUser()

        fun unbind()

    }


}