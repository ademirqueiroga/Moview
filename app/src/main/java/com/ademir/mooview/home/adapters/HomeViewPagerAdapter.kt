package com.ademir.mooview.home.adapters

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.ademir.mooview.R
import com.ademir.mooview.controllers.SessionController
import com.ademir.mooview.home.fragments.FeedFragment
import com.ademir.mooview.home.fragments.MovieListFragment
import com.ademir.mooview.home.fragments.ProfileFragment

/**
 * Created by ademir on 27/05/17.
 */
class HomeViewPagerAdapter(manager: FragmentManager, val context: Context): FragmentStatePagerAdapter(manager) {


    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> FeedFragment()
            1 -> MovieListFragment()
            else  -> ProfileFragment.newInstance(SessionController.user!!)
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> context.getString(R.string.text_feed)
            1 -> context.getString(R.string.text_movies)
            else -> context.getString(R.string.text_profile)
        }
    }

    override fun getCount() = 3

}