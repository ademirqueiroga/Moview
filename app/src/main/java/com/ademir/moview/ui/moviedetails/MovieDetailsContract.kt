package com.ademir.moview.ui.moviedetails

import com.ademir.moview.data.models.Movie
import com.ademir.moview.data.models.Video

interface MovieDetailsContract {

    interface View {

        fun setMovie(movie: Movie, video: Video?)

        fun showError()

        fun showLoader(show: Boolean)

    }

    interface Presenter {

        fun bindView(view: View)

        fun loadMovie(movieId: String)

        fun dispose()

    }

}