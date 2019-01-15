package com.ademir.moview

import android.app.Application
import android.content.Context
import com.ademir.moview.di.component.ApplicationComponent
import com.ademir.moview.di.component.DaggerApplicationComponent
import com.ademir.moview.di.module.ApplicationModule
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger

class MoviewApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()

        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this)
    }

    companion object {
        fun get(context: Context): MoviewApplication {
            return (context.applicationContext as MoviewApplication)
        }
    }
}