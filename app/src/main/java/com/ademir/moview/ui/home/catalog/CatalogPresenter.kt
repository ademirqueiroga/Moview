package com.ademir.moview.ui.home.catalog

import android.view.View
import com.ademir.moview.data.local.movie.MovieRepository
import com.ademir.moview.data.models.Movie
import javax.inject.Inject

/**
 * Created by ademir on 05/04/18.
 */
class CatalogPresenter @Inject constructor(movieRepository: MovieRepository) : CatalogContract.Presenter {

    var view: CatalogContract.View? = null

    override fun bindView(view: CatalogContract.View) {
        this.view = view
    }

    override fun unbind() {
        this.view = null
    }

    private val repoResult = movieRepository.popularMovies()

    val moviesPagedList = repoResult.pagedList
    val moviesNetworkState = repoResult.networkState
    val moviesRefreshState = repoResult.refreshState

    override fun refresh() {
        repoResult.refresh()
    }

    override fun onMovieClick(view: View, movie: Movie) {
        this.view?.showMovieDetailsUi(movie)
    }

    override fun onFavoriteClick(view: View, movie: Movie) {

    }

    override fun onCommentClick(view: View, movie: Movie) {

    }

    override fun onWatchlistClick(view: View, movie: Movie) {

    }


}