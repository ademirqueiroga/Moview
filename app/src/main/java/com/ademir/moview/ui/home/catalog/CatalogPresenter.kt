package com.ademir.moview.ui.home.catalog

import android.view.View
import com.ademir.moview.data.local.movie.MovieRepository
import com.ademir.moview.data.models.Movie

/**
 * Created by ademir on 05/04/18.
 */
class CatalogPresenter(val view: CatalogContract.View,
                       movieRepository: MovieRepository) : CatalogContract.Presenter {

    private val repoResult = movieRepository.popularMovies()

    val moviesPagedList = repoResult.pagedList
    val moviesNetworkState = repoResult.networkState
    val moviesRefreshState = repoResult.refreshState

    override fun refresh() {
        repoResult.refresh()
    }

    override fun onMovieClick(view: View, position: Int) {

    }

    override fun onFavoriteClick(view: View, movie: Movie) {

    }

    override fun onCommentClick(view: View, movie: Movie) {

    }

    override fun onWatchlistClick(view: View, movie: Movie) {

    }


}