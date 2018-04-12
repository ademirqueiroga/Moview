package com.ademir.moview.ui.home.catalog

import com.ademir.moview.home.adapters.MovieAdapter

/**
 * Created by ademir on 05/04/18.
 */
interface CatalogContract {

    interface View {

    }

    interface Presenter: MovieAdapter.OnMovieClickListener {

        fun bindView(view: CatalogContract.View)

        fun unbind()

        fun refresh()

    }

}