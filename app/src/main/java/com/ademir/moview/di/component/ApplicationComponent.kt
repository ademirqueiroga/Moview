package com.ademir.moview.di.component

import com.ademir.moview.MoviewApplication
import com.ademir.moview.di.module.ApplicationModule
import com.ademir.moview.home.fragments.CatalogFragment
import com.ademir.moview.ui.home.profile.ProfileFragment
import com.ademir.moview.ui.moviedetails.MovieDetailsActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(moviewApplication: MoviewApplication)

    fun inject(catalogFragment: CatalogFragment)

    fun inject(movieDetailsActivity: MovieDetailsActivity)

    fun inject(profileFragment: ProfileFragment)

}