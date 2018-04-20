package com.ademir.moview.ui.home.profile

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

/**
 * Created by ademir on 05/04/18.
 */
class ProfilePresenter @Inject constructor() : ProfileContract.Presenter {

    private var view: ProfileContract.View? = null

    override fun bindView(view: ProfileContract.View) {
        this.view = view
    }

    override fun loadUser() {
        view?.setUser(FirebaseAuth.getInstance().currentUser)
    }

    override fun unbind() {
        this.view = null
    }

}