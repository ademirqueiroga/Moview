package com.ademir.mooview.home.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.ademir.mooview.home.fragments.FeedFragment
import com.ademir.mooview.home.fragments.MovieListFragment


class ProfileViewPagerAdapter(manager: FragmentManager): FragmentStatePagerAdapter(manager) {

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> FeedFragment.newInstance(FeedFragment.USER_TIMELINE)
            1 -> MovieListFragment.newInstance(MovieListFragment.WATCHLIST)
            else -> MovieListFragment.newInstance(MovieListFragment.FAVORITES)
        }
    }

    override fun getPageTitle(position: Int) = when(position) {
        0 -> "Timeline"
        1 -> "Watchlist"
        else -> "Favorites"
    }

    override fun getCount() = 3


}