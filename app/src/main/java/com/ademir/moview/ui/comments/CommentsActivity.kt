package com.ademir.moview.ui.comments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.app.AppCompatActivity
import com.ademir.moview.R
import com.ademir.moview.commons.Constants
import com.ademir.moview.SessionController
import com.ademir.moview.App
import com.ademir.moview.data.models.Comment
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_comments.*

class CommentsActivity : AppCompatActivity() {

    private val movieId by lazy { intent.getIntExtra(Constants.EXTRA_MOVIE_ID, -1) }
    private val pagerAdapter by lazy { comments_viewpager.adapter as CommentsPagerAdapter }
    private var disposable: CompositeDisposable? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)
        setSupportActionBar(toolbar)

        disposable = CompositeDisposable()

        btn_send.setOnClickListener { sendComment() }
        et_comment.setOnEditorActionListener { _, _, _ ->
            sendComment()
            true
        }

        disposable!!.add(
                RxTextView.afterTextChangeEvents(et_comment)
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { btn_send.isEnabled = it.editable()!!.isNotEmpty() }
        )

        setupTabs()
    }

    override fun onStop() {
        super.onStop()
        disposable?.clear()
    }

    private fun sendComment() {
        btn_send.isEnabled = false
//        App.apiService!!.sendComment(SessionController.user!!.token, movieId, et_comment.text.toString())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnTerminate { et_comment.text.clear() }
//                .subscribe({ pagerAdapter.notifyCommentAdded(it) }, Throwable::printStackTrace)
    }

    private fun setupTabs() {
        comments_viewpager.adapter = CommentsPagerAdapter(this, movieId, supportFragmentManager)
        comments_tabs.setupWithViewPager(comments_viewpager)
    }

    companion object {

        fun createIntent(context: Context, movieId: Int): Intent {
            val intent = Intent(context, CommentsActivity::class.java)
            intent.putExtra(Constants.EXTRA_MOVIE_ID, movieId)
            return intent
        }

    }

    class CommentsPagerAdapter(val context: Context, val movieId: Int, manager: FragmentManager) : FragmentStatePagerAdapter(manager) {

        companion object {
            val ALL_COMMENTS = 0
            val FOLLOWING_COMMENTS = 1
        }

        private lateinit var allCommentsFragment: CommentsFragment

        override fun getItem(position: Int): Fragment {
            return when (position) {
                ALL_COMMENTS -> {
                    allCommentsFragment = CommentsFragment.newInstance(movieId, ALL_COMMENTS)
                    allCommentsFragment
                }
                else -> CommentsFragment.newInstance(movieId, FOLLOWING_COMMENTS)
            }
        }

        override fun getCount() = 2

        override fun getPageTitle(position: Int): CharSequence? {
            return when (position) {
                ALL_COMMENTS -> context.getString(R.string.text_all)

                else -> context.getString(R.string.text_following)
            }
        }

        fun notifyCommentAdded(comment: Comment) {
            allCommentsFragment.add(comment)
        }

    }

}
