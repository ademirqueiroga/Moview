package com.ademir.moview

import android.app.Application
import com.ademir.moview.data.local.MoviewDb
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import java.util.concurrent.Executor
import java.util.concurrent.Executors


class App : Application() {

    companion object {
        lateinit var database: MoviewDb
        val DISK_IO: Executor = Executors.newSingleThreadExecutor()
    }

    override fun onCreate() {
        super.onCreate()

        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this)

        database = MoviewDb.create(this)
    }

}