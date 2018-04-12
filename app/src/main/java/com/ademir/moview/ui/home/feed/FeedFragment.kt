package com.ademir.moview.home.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ademir.moview.R
import com.ademir.moview.commons.prepare
import com.ademir.moview.home.adapters.FeedAdapter
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_feed.*

/**
 * Created by ademir on 27/05/17.
 */
class FeedFragment : Fragment() {

    private var type = -1

    private val adapter by lazy { rv_feed.adapter as FeedAdapter }
    private var disposable: Disposable? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_feed.prepare(FeedAdapter(), LinearLayoutManager(context))
        feed_refresh_layout.setOnRefreshListener { getFeed() }
        getFeed()
    }

    override fun onStop() {
        super.onStop()
        disposable?.dispose()
    }

    private fun getFeed() {
//        val observable = when (type) {
//            DEFAULT -> MoviewApplication.apiService!!.getFeed(SessionController.user!!.token, "true")
//            else -> MoviewApplication.apiService!!.getFeed(SessionController.user!!.token, "false")
//        }
//
//        disposable = observable.observeOn(AndroidSchedulers.mainThread())
//                .doOnTerminate { feed_refresh_layout.isRefreshing = false }
//                .subscribe({ adapter.setData(it) }, Throwable::printStackTrace)
    }

    companion object {

        const val DEFAULT = 0
        const val USER_TIMELINE = 1

        fun newInstance(type: Int = FeedFragment.DEFAULT): FeedFragment {
            val frag = FeedFragment()
            frag.type = type
            return frag
        }
    }

}