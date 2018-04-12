package com.ademir.moview.di.component

import com.ademir.moview.MoviewApplication
import com.ademir.moview.data.local.MoviewDb
import com.ademir.moview.data.local.movie.MovieRepository
import com.ademir.moview.data.remote.TmdbApiInterface
import com.ademir.moview.di.IoExecutor
import com.ademir.moview.di.module.ApplicationModule
import com.ademir.moview.home.fragments.CatalogFragment
import dagger.Component
import java.util.concurrent.Executor
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(moviewApplication: MoviewApplication)

    fun inject(catalogFragment: CatalogFragment)

    fun getDatabase(): MoviewDb

    fun getTmdbApi(): TmdbApiInterface

    fun getExecutor(): Executor

    fun getMovieRepository(): MovieRepository

}