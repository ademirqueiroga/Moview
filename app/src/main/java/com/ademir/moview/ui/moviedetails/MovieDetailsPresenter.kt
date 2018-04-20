package com.ademir.moview.ui.moviedetails

import com.ademir.moview.data.remote.TmdbApiInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class MovieDetailsPresenter @Inject constructor(
        private val apiInterface: TmdbApiInterface) : MovieDetailsContract.Presenter {

    private var view: MovieDetailsContract.View? = null
    private var disposable: Disposable? = null

    override fun bindView(view: MovieDetailsContract.View) {
        this.view = view
    }

    override fun loadMovie(movieId: String) {
        disposable = apiInterface.getMovie(movieId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ view?.setMovie(it) }, { view?.showError() })
    }

    override fun dispose() {
        disposable?.dispose()
        this.view = null
    }

}