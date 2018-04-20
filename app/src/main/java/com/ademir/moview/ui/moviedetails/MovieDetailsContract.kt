package com.ademir.moview.ui.moviedetails

import com.ademir.moview.data.models.Movie

interface MovieDetailsContract {

    interface View {

        fun setMovie(movie: Movie)

        fun showError()

    }

    interface Presenter {

        fun bindView(view: View)

        fun loadMovie(movieId: String)

        fun dispose()

    }

}