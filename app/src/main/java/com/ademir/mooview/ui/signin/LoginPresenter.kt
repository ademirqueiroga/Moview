package com.ademir.mooview.login

import android.content.Context
import android.widget.Toast
import com.ademir.mooview.R
import com.ademir.mooview.SessionController
import com.ademir.mooview.App
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable


class LoginPresenter(var view: LoginContract.View): LoginContract.Presenter {

    private var disposable: Disposable? = null

    override fun unsubscribe() {
        disposable?.dispose()
    }

    override fun login(context: Context, username: String, password: String) {
        view.showProgressDialog(true)
        disposable = App.apiService!!.login(username, password)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate { view.showProgressDialog(false) }
                .subscribe(
                        {
                            SessionController.user = it.user
                            SessionController.createSession(context, it.user!!)
                            view.showHomeUI()
                        },
                        { Toast.makeText(context, R.string.error_try_again, Toast.LENGTH_LONG).show() }
                )
    }

}
