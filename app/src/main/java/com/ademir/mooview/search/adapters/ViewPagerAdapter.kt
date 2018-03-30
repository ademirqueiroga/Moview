package com.ademir.mooview.search.adapters

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.ademir.mooview.search.fragments.RecyclerViewFragment

/**
 * Created by ademir on 28/05/17.
 */
class ViewPagerAdapter(val fragments: ArrayList<RecyclerViewFragment>, fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int) = fragments[position]

    override fun getCount() = fragments.size

    override fun getPageTitle(position: Int) = fragments[position].title
}