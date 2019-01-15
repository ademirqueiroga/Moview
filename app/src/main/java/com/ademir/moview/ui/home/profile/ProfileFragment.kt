package com.ademir.moview.ui.home.profile

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ademir.moview.MoviewApplication
import com.ademir.moview.R
import com.ademir.moview.commons.CircleTransform
import com.ademir.moview.commons.load
import com.ademir.moview.home.fragments.CatalogFragment
import com.ademir.moview.home.fragments.FeedFragment
import com.ademir.moview.login.SignInActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.profile_details.*
import javax.inject.Inject


class ProfileFragment : androidx.fragment.app.Fragment(), ProfileContract.View {

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

        profile_background.setOnClickListener {
            val selectPhotoIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (selectPhotoIntent.resolveActivity(activity?.packageManager) != null) {
                startActivityForResult(selectPhotoIntent, REQUEST_IMAGE_CAPTURE)
            }
        }

        presenter.bindView(this)
        presenter.loadUser()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            data?.let {
                val extras = data.extras
                iv_picture.setImageBitmap(extras.get("data") as Bitmap)
            }
        }
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
            user.providerData.getOrNull(1)?.photoUrl.let {
                iv_picture.load(it.toString(), transformation = CircleTransform())
            }
            tv_username.text = user.email
            tv_name.text = user.displayName
        }
    }

    override fun showSignInUi() {
        FirebaseAuth.getInstance().signOut()
        activity?.finish()
        startActivity(Intent(context, SignInActivity::class.java))
    }

    class ProfileViewPagerAdapter(manager: androidx.fragment.app.FragmentManager) : androidx.fragment.app.FragmentStatePagerAdapter(manager) {

        override fun getItem(position: Int): androidx.fragment.app.Fragment {
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

        const val REQUEST_IMAGE_CAPTURE = 1233

        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }

}

