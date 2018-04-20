package com.ademir.moview.ui.home.catalog

import com.ademir.moview.data.models.Movie
import com.ademir.moview.home.adapters.MovieAdapter

/**
 * Created by ademir on 05/04/18.
 */
interface CatalogContract {

    interface View {

        fun showMovieDetailsUi(movie: Movie)

    }

    interface Presenter : MovieAdapter.OnMovieClickListener {

        fun bindView(view: CatalogContract.View)

        fun unbind()

        fun refresh()

    }

}