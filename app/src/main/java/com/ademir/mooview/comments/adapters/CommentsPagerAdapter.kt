package com.ademir.mooview.comments.adapters

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.ademir.mooview.R
import com.ademir.mooview.comments.fragments.CommentsFragment
import com.ademir.mooview.model.Comment

/**
 * Created by ademir on 28/05/17.
 */
class CommentsPagerAdapter(val context: Context, val movieId: Int, manager: FragmentManager): FragmentStatePagerAdapter(manager) {

    companion object {
        val ALL_COMMENTS = 0
        val FOLLOWING_COMMENTS = 1
    }

    private lateinit var allCommentsFragment: CommentsFragment

    override fun getItem(position: Int): Fragment {
        return when (position) {
            ALL_COMMENTS ->  {
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

    fun notifyCommentAdded(comment: Comment) { allCommentsFragment.add(comment) }

}