package com.ademir.moview.home

import android.app.Activity
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.util.TypedValue.COMPLEX_UNIT_DIP
import android.util.TypedValue.applyDimension
import android.view.Menu
import android.view.MenuItem
import com.ademir.moview.R
import com.ademir.moview.SessionController
import com.ademir.moview.home.fragments.CatalogFragment
import com.ademir.moview.home.fragments.FeedFragment
import com.ademir.moview.home.fragments.ProfileFragment
import com.ademir.moview.login.SignInActivity
import kotlinx.android.synthetic.main.content_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        setDefaultKeyMode(Activity.DEFAULT_KEYS_SEARCH_LOCAL)
        setupViewPager()
        setupTabs()
    }

    private fun setupViewPager() = with(home_view_pager) {
        adapter = HomeViewPagerAdapter(supportFragmentManager, this@HomeActivity)
        offscreenPageLimit = 3
        pageMargin = applyDimension(COMPLEX_UNIT_DIP, 2f, resources.displayMetrics).toInt()
    }

    private fun setupTabs() = with(home_tabs) {
        setViewPager(home_view_pager)
        setAllCaps(true)
        setTextColorResource(R.color.white)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu!!)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setIconifiedByDefault(true)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.action_settings -> return true
            R.id.action_logout -> logout()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun logout() {
        SessionController.clearSession(this)
        startActivity(Intent(this, SignInActivity::class.java))
        finish()
    }

    class HomeViewPagerAdapter(manager: FragmentManager, val context: Context) : FragmentStatePagerAdapter(manager) {


        override fun getItem(position: Int): Fragment {
            return when (position) {
//                0 -> FeedFragment()
                0 -> CatalogFragment()
                else -> ProfileFragment.newInstance(SessionController.user!!)
            }
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return when (position) {
                0 -> context.getString(R.string.text_feed)
                1 -> context.getString(R.string.text_movies)
                else -> context.getString(R.string.text_profile)
            }
        }

        override fun getCount() = 1 // TODO

    }

}
