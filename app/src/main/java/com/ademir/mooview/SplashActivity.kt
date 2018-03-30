package com.ademir.mooview

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ademir.mooview.controllers.SessionController
import com.ademir.mooview.home.HomeActivity
import com.ademir.mooview.login.LoginActivity
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
                                startActivity(Intent(this, LoginActivity::class.java))
                            }
                        }
                )

    }
}
