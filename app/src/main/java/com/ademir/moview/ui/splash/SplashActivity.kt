package com.ademir.moview.ui.splash

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ademir.moview.SessionController
import com.ademir.moview.home.HomeActivity
import com.ademir.moview.login.SignInActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SessionController.loadSession(this)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .delay(1500, TimeUnit.MILLISECONDS)
                .subscribe(
                        {
                            finish()
                            if (it) {
                                startActivity(Intent(this, HomeActivity::class.java))
                            } else {
                                startActivity(Intent(this, SignInActivity::class.java))
                            }
                        }
                )

    }
}
