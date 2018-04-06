package com.ademir.mooview.home.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ademir.mooview.R
import com.ademir.mooview.ui.comments.CommentsActivity
import com.ademir.mooview.commons.prepare
import com.ademir.mooview.SessionController
import com.ademir.mooview.App
import com.ademir.mooview.home.CatalogActivity
import com.ademir.mooview.home.adapters.MovieAdapter
import com.ademir.mooview.data.models.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_movie_list.*

/**
 * Created by ademir on 27/05/17.
 */
class CatalogFragment : Fragment(), MovieAdapter.OnMovieClickListener {

    private var type = -1

    private val adapter by lazy { recycler_view.adapter as MovieAdapter }
    private var disposable: Disposable? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipe_refresh_layout.setOnRefreshListener { loadContent() }
        recycler_view.prepare(MovieAdapter(this@CatalogFragment), LinearLayoutManager(context))
        loadContent()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }

    private fun loadContent() {
        val observable = when (type) {
            DEFAULT -> App.apiService.getMovies()
            WATCHLIST -> App.apiService.getWatchlist(SessionController.user!!.token)
            else -> App.apiService.getFavorites(SessionController.user!!.token)
        }

        disposable = observable.observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate { swipe_refresh_layout.isRefreshing = false }
                .subscribe({ adapter.setData(it.movies) }, { it.printStackTrace() })
    }

    override fun onMovieClick(view: View, position: Int) {
        context?.let {
            startActivity(CatalogActivity.createIntent(it, adapter.getMovieId(position)))
        }
    }

    override fun onFavoriteClick(view: View, movie: Movie) {
        App.apiService.addToFavorites(SessionController.user!!.token, movie.id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ }, Throwable::printStackTrace)
    }

    override fun onWatchlistClick(view: View, movie: Movie) {
        App.apiService.addToWatchlist(SessionController.user!!.token, movie.id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ }, Throwable::printStackTrace)
    }

    override fun onCommentClick(view: View, movie: Movie) {
        context?.let {
            startActivity(CommentsActivity.createIntent(it, movie.id))
        }
    }

    companion object {
        const val DEFAULT = 0
        const val WATCHLIST = 1
        const val FAVORITES = 2

        fun newInstance(type: Int = CatalogFragment.DEFAULT): CatalogFragment {
            val frag = CatalogFragment()
            frag.type = type
            return frag
        }

    }

}