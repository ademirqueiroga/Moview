package com.ademir.mooview.home.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ademir.mooview.R
import com.ademir.moview.model.User
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.profile_details.*


class ProfileFragment : Fragment() {

    private lateinit var user: User

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTabs()
        setUser(user)
    }

    private fun setupTabs() {
        profile_nested_scroll.isFillViewport = true
        fragmentManager?.let {
            profile_viewpager.adapter = ProfileViewPagerAdapter(it)
            profile_tabs.setupWithViewPager(profile_viewpager)
        }
    }

    private fun setUser(user: User) = with(user) {
        tv_username.text = String.format("@%s", username)
        tv_name.text = user.name
        tv_followers.text = String.format("%d followers", user.followersCount)
        tv_following.text = String.format("%d following", user.followingCount)
    }

    companion object {
        fun newInstance(user: User): ProfileFragment {
            val frag = ProfileFragment()
            frag.user = user
            return frag
        }
    }

    class ProfileViewPagerAdapter(manager: FragmentManager) : FragmentStatePagerAdapter(manager) {

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> FeedFragment.newInstance(FeedFragment.USER_TIMELINE)
                1 -> CatalogFragment.newInstance(CatalogFragment.WATCHLIST)
                else -> CatalogFragment.newInstance(CatalogFragment.FAVORITES)
            }
        }

        override fun getPageTitle(position: Int) = when (position) {
            0 -> "Timeline"
            1 -> "Watchlist"
            else -> "Favorites"
        }

        override fun getCount() = 3


    }

}
