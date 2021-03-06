package com.ademir.moview.home.fragments

import androidx.lifecycle.Observer
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ademir.moview.MoviewApplication
import com.ademir.moview.R
import com.ademir.moview.commons.NetworkState
import com.ademir.moview.commons.prepare
import com.ademir.moview.data.models.Movie
import com.ademir.moview.ui.moviedetails.MovieDetailsActivity
import com.ademir.moview.ui.home.catalog.CatalogContract
import com.ademir.moview.ui.home.catalog.CatalogPresenter
import com.ademir.moview.ui.home.catalog.adapters.PagedListMovieAdapter
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_movie_list.*
import javax.inject.Inject

/**
 * Created by ademir on 27/05/17.
 */
class CatalogFragment : androidx.fragment.app.Fragment(), CatalogContract.View {

    private var type = -1

    @Inject
    lateinit var presenter: CatalogPresenter
    private lateinit var adapter: PagedListMovieAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let {
            MoviewApplication.get(it)
                    .applicationComponent
                    .inject(this)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.bindView(this)

        adapter = PagedListMovieAdapter(presenter)

        swipe_refresh_layout.setOnRefreshListener { presenter.refresh() }

        recycler_view.prepare(adapter, androidx.recyclerview.widget.LinearLayoutManager(context))
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
        presenter.unbind()
    }

    override fun showMovieDetailsUi(movie: Movie) {
        context?.let {
            startActivity(MovieDetailsActivity.createIntent(it, movie))
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