package com.ademir.moview.home.fragments

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ademir.moview.App
import com.ademir.moview.R
import com.ademir.moview.commons.NetworkState
import com.ademir.moview.commons.prepare
import com.ademir.moview.data.local.movie.MovieRepository
import com.ademir.moview.data.remote.MoviewApi
import com.ademir.moview.home.adapters.MovieAdapter
import com.ademir.moview.ui.home.catalog.CatalogContract
import com.ademir.moview.ui.home.catalog.CatalogPresenter
import com.ademir.moview.ui.home.catalog.adapters.PagedListMovieAdapter
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_movie_list.*

/**
 * Created by ademir on 27/05/17.
 */
class CatalogFragment : Fragment(), CatalogContract.View {

    private var type = -1

    private lateinit var presenter: CatalogPresenter
    private lateinit var adapter: PagedListMovieAdapter
    private var disposable: Disposable? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = CatalogPresenter(this, MovieRepository(App.database, MoviewApi.tmdbApi, App.DISK_IO))
        adapter = PagedListMovieAdapter(presenter)

        swipe_refresh_layout.setOnRefreshListener { presenter.refresh() }

        recycler_view.prepare(adapter, LinearLayoutManager(context))
        with(presenter) {
            moviesPagedList.observe(this@CatalogFragment, Observer {
                adapter.submitList(it)
            })

            moviesRefreshState.observe(this@CatalogFragment, Observer {
                swipe_refresh_layout.isRefreshing = it == NetworkState.LOADING
            })

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }

//    override fun onMovieClick(view: View, position: Int) {
////        context?.let {
////            startActivity(MovieActivity.createIntent(it, adapter.getMovieId(position)))
////        }
//    }
//
//    override fun onFavoriteClick(view: View, movie: Movie) {
////        App.apiService.addToFavorites(SessionController.user!!.token, movie.id)
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe({ }, Throwable::printStackTrace)
//    }
//
//    override fun onWatchlistClick(view: View, movie: Movie) {
////        App.apiService.addToWatchlist(SessionController.user!!.token, movie.id)
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe({ }, Throwable::printStackTrace)
//    }
//
//    override fun onCommentClick(view: View, movie: Movie) {
////        context?.let {
////            startActivity(CommentsActivity.createIntent(it, movie.id))
////        }
//    }

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