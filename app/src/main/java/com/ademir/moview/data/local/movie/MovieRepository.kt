package com.ademir.moview.data.local.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.annotation.MainThread
import android.util.Log
import com.ademir.moview.commons.Listing
import com.ademir.moview.commons.NetworkState
import com.ademir.moview.commons.enqueue
import com.ademir.moview.data.local.MoviewDb
import com.ademir.moview.data.models.Movie
import com.ademir.moview.data.remote.TmdbApiInterface
import java.util.concurrent.Executor
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by ademir on 05/04/18.
 */
@Singleton
class MovieRepository @Inject constructor(private val db: MoviewDb,
                                          private val moviewTmdbApi: TmdbApiInterface,
                                          private val ioExecutor: Executor) {

    private val TAG = MovieRepository::class.java.simpleName

    /**
     * Inserts the response into the database while also assigning position indices to items.
     */
    private fun insertResultInDb(data: Movie.Payload?) {
        Log.i(TAG, "INSERTING ${data?.results?.size} Movies in DataBase")
        data?.results?.let {
            ioExecutor.execute {
                db.movieDao().insert(*data.results.toTypedArray())
            }
        }
    }

    /**
     * When refresh is called, we simply run a fresh network request and when it arrives, clear
     * the database table and insert movies new items in a transaction.
     * <p>
     * Since the PagedList already uses a database bound data source, it will automatically be
     * updated after the database transaction is finished.
     */
    @MainThread
    private fun refresh(): LiveData<NetworkState> {

        Log.d(TAG, "REFRESH. The DataBase will be cleaned and the incoming offers saved")

        val networkState = MutableLiveData<NetworkState>()
        networkState.value = NetworkState.LOADING

        moviewTmdbApi.getPopular()
                .enqueue(
                        {
                            ioExecutor.execute {
                                db.runInTransaction {
                                    db.movieDao().dropTable()
                                    insertResultInDb(it)
                                }
                                networkState.postValue(NetworkState.LOADED)
                            }
                        },
                        { networkState.value = NetworkState.error(it.message) }
                )

        return networkState
    }

    fun popularMovies(): Listing<Movie> {

        Log.d(TAG, "Creating Movie Listing object")

        val boundaryCallback = MovieBoundaryCallback(
                movieDao = db.movieDao(),
                moviewTmdbApi = moviewTmdbApi,
                ioExecutor = ioExecutor,
                responseHandler = this::insertResultInDb,
                pageSize = DEFAULT_PAGE_SIZE)

        // Create a data source from Room
        val dataSourceFactory = db.movieDao().movies()
        val livePagedListBuilder = LivePagedListBuilder(dataSourceFactory, DEFAULT_PAGE_SIZE)
                .setBoundaryCallback(boundaryCallback)

        // we are using a mutable live data to trigger refresh requests which eventually calls
        // refresh method and gets a new live data. Each refresh request by the user becomes a newly
        // dispatched data in refreshTrigger
        val refreshTrigger = MutableLiveData<Unit>()
        val refreshState = Transformations.switchMap(refreshTrigger, {
            refresh()
        })

        return Listing(
                pagedList = livePagedListBuilder.build(),
                networkState = boundaryCallback.networkState,
                refreshState = refreshState,
                refresh = { refreshTrigger.value = null },
                retry = { boundaryCallback.helper.retryAllFailed() }
        )

    }

    companion object {
        const val DEFAULT_PAGE_SIZE = 20
    }

}