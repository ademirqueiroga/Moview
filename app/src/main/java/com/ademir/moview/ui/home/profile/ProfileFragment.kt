package com.ademir.moview.ui.home.profile

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ademir.moview.MoviewApplication
import com.ademir.moview.R
import com.ademir.moview.commons.load
import com.ademir.moview.home.fragments.CatalogFragment
import com.ademir.moview.home.fragments.FeedFragment
import com.ademir.moview.login.SignInActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.profile_details.*
import javax.inject.Inject


class ProfileFragment : Fragment(), ProfileContract.View {

    private var user: FirebaseUser? = null

    @Inject
    lateinit var presenter: ProfilePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        context?.let {
            MoviewApplication.get(it).applicationComponent.inject(this)
        }

        this.user = FirebaseAuth.getInstance().currentUser
        if (this.user == null) {
            showSignInUi()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTabs()
        presenter.bindView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbind()
    }

    private fun setupTabs() {
        profile_nested_scroll.isFillViewport = true
        fragmentManager?.let {
            profile_viewpager.adapter = ProfileViewPagerAdapter(it)
            profile_tabs.setupWithViewPager(profile_viewpager)
        }
    }

    override fun setUser(user: FirebaseUser?) {
        if (user == null) {
            showSignInUi()
        } else {
            iv_picture.load(user.photoUrl.toString())
            tv_username.text = user.email
            tv_name.text = user.displayName
//        tv_followers.text = String.format("%d followers", user.followersCount)
//        tv_following.text = String.format("%d following", user.followingCount)
        }
    }

    override fun showSignInUi() {
        FirebaseAuth.getInstance().signOut()
        activity?.finish()
        startActivity(Intent(context, SignInActivity::class.java))
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

    companion object {
        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }

}

