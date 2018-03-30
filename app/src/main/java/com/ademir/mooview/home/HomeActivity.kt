package com.ademir.mooview.home

import android.app.Activity
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.util.TypedValue.COMPLEX_UNIT_DIP
import android.util.TypedValue.applyDimension
import android.view.Menu
import android.view.MenuItem
import com.ademir.mooview.R
import com.ademir.mooview.controllers.SessionController
import com.ademir.mooview.home.adapters.HomeViewPagerAdapter
import com.ademir.mooview.login.LoginActivity
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
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

}
