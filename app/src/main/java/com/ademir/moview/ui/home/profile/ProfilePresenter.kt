package com.ademir.moview.ui.home.profile

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

/**
 * Created by ademir on 05/04/18.
 */
class ProfilePresenter @Inject constructor() : ProfileContract.Presenter {

    private var view: ProfileContract.View? = null
    private var user: FirebaseUser? = null

    override fun bindView(view: ProfileContract.View) {
        this.view = view
        user?.let { view.setUser(it) }
    }

    override fun loadUser() {
        user = FirebaseAuth.getInstance().currentUser
        view?.setUser(user)
    }

    override fun unbind() {
        view = null
    }

}