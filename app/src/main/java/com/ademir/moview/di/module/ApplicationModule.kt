package com.ademir.moview.di.module

import android.content.Context
import com.ademir.moview.MoviewApplication
import com.ademir.moview.data.local.MoviewDb
import com.ademir.moview.data.local.movie.MovieRepository
import com.ademir.moview.data.remote.MoviewApi
import com.ademir.moview.data.remote.TmdbApiInterface
import com.ademir.moview.di.ApplicationContext
import com.ademir.moview.di.IoExecutor
import com.ademir.moview.ui.moviedetails.MovieDetailsPresenter
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.ThreadFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApplicationModule(val application: MoviewApplication) {

    @ApplicationContext
    @Provides
    fun provideContext(): Context = application

    @Singleton
    @Provides
    fun provideDatabase(): MoviewDb {
        return MoviewDb.create(application)
    }

    @Singleton
    @Provides
    fun provideTmdbApi(): TmdbApiInterface {
        return MoviewApi.tmdbApi
    }

    @Singleton
    @Provides
    fun provideExecutor(): Executor {
        return Executors.newSingleThreadExecutor()
    }

}