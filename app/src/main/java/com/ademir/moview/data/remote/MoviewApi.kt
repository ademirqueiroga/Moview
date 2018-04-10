package com.ademir.moview.data.remote

import com.ademir.moview.commons.Constants
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object MoviewApi {

    private val rxJavaAdapter = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())

    private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
            .build()

    val tmdbApi = Retrofit.Builder()
            .baseUrl(Constants.Urls.TMDB_DOMAIN)
            .client(okHttpClient)
            .addCallAdapterFactory(rxJavaAdapter)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(TmdbApiInterface::class.java)

}