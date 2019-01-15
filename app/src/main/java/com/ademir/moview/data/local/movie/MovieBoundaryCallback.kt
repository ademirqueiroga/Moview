package com.ademir.moview.data.local.movie

import androidx.paging.PagedList
import androidx.annotation.MainThread
import android.util.Log
import com.ademir.moview.commons.PagingRequestHelper
import com.ademir.moview.commons.createStatusLiveData
import com.ademir.moview.commons.enqueue
import com.ademir.moview.data.models.Movie
import com.ademir.moview.data.remote.TmdbApiInterface
import java.util.concurrent.Executor
import javax.inject.Singleton

/**
 * Created by ademir on 05/04/18.
 */
class MovieBoundaryCallback(
        private val movieDao: MovieDao,
        private val moviewTmdbApi: TmdbApiInterface,
        private val ioExecutor: Executor,
        private val responseHandler: (Movie.Payload?) -> Unit,
        private val pageSize: Int
) : PagedList.BoundaryCallback<Movie>() {

    private val TAG = MovieBoundaryCallback::class.java.simpleName

    val helper = PagingRequestHelper(ioExecutor)
    val networkState = helper.createStatusLiveData()

    /**
     * Database returned 0 items. We should query the backend for more items.
     */
    @MainThread
    override fun onZeroItemsLoaded() {
        Log.d(TAG, "onZeroItemsLoaded()")
        helper.runIfNotRunning(PagingRequestHelper.RequestType.INITIAL) {
            ioExecutor.execute {
                val page: Int = (movieDao.count() / pageSize) + 1
                moviewTmdbApi.getPopular(page)
                        .enqueue(
                                { data -> insertItemsIntoDb(data, it) },
                                { fail -> it.recordFailure(fail) }
                        )
            }
        }
    }

    /**
     * User reached to the end of the list.
     */
    @MainThread
    override fun onItemAtEndLoaded(itemAtEnd: Movie) {
        Log.d(TAG, "onItemAtEndLoaded()")
        helper.runIfNotRunning(PagingRequestHelper.RequestType.AFTER) {
            ioExecutor.execute {
                val page: Int = (movieDao.count() / pageSize) + 1
                Log.d("REQUESTING_PAGE", page.toString())
                moviewTmdbApi.getPopular(page)
                        .enqueue(
                                { data -> insertItemsIntoDb(data, it) },
                                { fail -> it.recordFailure(fail) }
                        )
            }
        }
    }

    override fun onItemAtFrontLoaded(itemAtFront: Movie) {
        // ignored, since we only ever append to what's in the DB
    }

    /**
     * every time it gets new items, boundary callback simply inserts them into the database and
     * paging library takes care of refreshing the list if necessary.
     */
    @Suppress("NOTHING_TO_INLINE")
    private inline fun insertItemsIntoDb(payload: Movie.Payload?,
                                         it: PagingRequestHelper.Request.Callback) {
        responseHandler(payload)
        it.recordSuccess()
    }

}