package com.ademir.moview

import android.app.Application
import com.ademir.moview.data.local.MoviewDb
import com.ademir.moview.di.component.ApplicationComponent
import com.ademir.moview.di.module.ApplicationModule
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import dagger.internal.DaggerCollections
import javax.inject.Inject


class MoviewApplication : Application() {
    

    override fun onCreate() {
        super.onCreate()
        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this)
    }

}