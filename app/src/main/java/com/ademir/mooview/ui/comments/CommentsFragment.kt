package com.ademir.mooview.ui.comments

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ademir.mooview.R
import com.ademir.mooview.commons.prepare
import com.ademir.mooview.SessionController
import com.ademir.mooview.App
import com.ademir.mooview.data.models.Comment
import com.ademir.mooview.ui.comments.adapters.CommentAdapter
import com.ademir.mooview.ui.comments.adapters.CommentsPagerAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_comments.*

/**
 * Created by ademir on 28/05/17.
 */
class CommentsFragment : Fragment() {

    private var movieId: Int = -1
    private var contentType: Int = -1

    private var disposable: Disposable? = null
    private val adapter by lazy { recycler_view.adapter as CommentAdapter }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_comments, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let {
            val adapter = CommentAdapter(it)
            recycler_view.prepare(adapter, LinearLayoutManager(it, LinearLayoutManager.VERTICAL, true))
        }
        loadComments()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }

    fun add(comment: Comment) {
        adapter.add(comment)
        recycler_view.scrollToPosition(0)
    }

    private fun loadComments() {
        if (adapter.itemCount == 0) showProgressBar(true)

        val query: String
        if (contentType == CommentsPagerAdapter.FOLLOWING_COMMENTS) {
            query = "true"
        } else {
            query = "false"
        }

        disposable =
                App.apiService!!.getMovieComments(SessionController.user!!.token, movieId, query)
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnTerminate { showProgressBar(false) }
                        .subscribe(
                                { adapter.addAll(it) },
                                {
                                    Snackbar.make(recycler_view, R.string.error_fetch_comments, Snackbar.LENGTH_INDEFINITE)
                                            .setAction(R.string.text_try_again) { loadComments() }
                                            .show()
                                }
                        )

    }

    private fun showProgressBar(show: Boolean) = with(comments_progress) {
        if (show) visibility = View.VISIBLE else visibility = View.GONE
    }

    companion object {
        fun newInstance(movieId: Int, contentType: Int): CommentsFragment {
            val frag = CommentsFragment()
            frag.movieId = movieId
            frag.contentType = contentType
            return frag
        }
    }

}