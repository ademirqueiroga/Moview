package com.ademir.mooview.core

import android.app.Application
import com.ademir.mooview.commons.Constants
import com.ademir.mooview.rest.ApiInterface
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


class App : Application() {

    companion object {
        lateinit var apiService: ApiInterface
    }

    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder()
                .client(OkHttpClient.Builder().build())
                .baseUrl(Constants.Urls.DOMAIN)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()

        apiService = retrofit.create(ApiInterface::class.java)
    }

}