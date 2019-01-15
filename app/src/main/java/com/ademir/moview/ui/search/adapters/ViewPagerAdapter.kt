package com.ademir.moview.search.adapters

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.ademir.moview.search.fragments.RecyclerViewFragment

/**
 * Created by ademir on 28/05/17.
 */
class ViewPagerAdapter(val fragments: ArrayList<RecyclerViewFragment>, fm: androidx.fragment.app.FragmentManager) : androidx.fragment.app.FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int) = fragments[position]

    override fun getCount() = fragments.size

    override fun getPageTitle(position: Int) = fragments[position].title
}