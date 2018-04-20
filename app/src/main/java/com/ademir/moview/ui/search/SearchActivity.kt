package com.ademir.moview.search

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.View
import com.ademir.moview.R
import com.ademir.moview.home.adapters.MovieAdapter
import com.ademir.moview.data.models.Movie
import com.ademir.moview.data.remote.payloads.SearchPayload
import com.ademir.moview.search.adapters.ViewPagerAdapter
import com.ademir.moview.search.fragments.RecyclerViewFragment
import com.ademir.moview.model.User
import com.ademir.moview.search.adapters.UserAdapter
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.content_search.*

class SearchActivity : AppCompatActivity(), UserAdapter.UserClickListener, MovieAdapter.OnMovieClickListener {

    companion object {
        const val MOVIES = "movies"
        const val USERS = "users"
    }

    private val fragments = HashMap<String, RecyclerViewFragment>()
    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setSupportActionBar(toolbar)
        handleIntent(intent)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    override fun onStop() {
        super.onStop()
        disposable?.dispose()
    }

    private fun handleIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            supportActionBar!!.title = query
            search(query)
        }
    }

    private fun search(query: String) {
//        disposable = App.apiService.search(SessionController.user!!.token, query)
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnTerminate { search_progress.visibility = View.GONE }
//                .subscribe({ handleSearchResponse(it) }, Throwable::printStackTrace)
    }

    private fun handleSearchResponse(payload: SearchPayload) {
        val fragments = ArrayList<RecyclerViewFragment>(3)

        val movies = payload.movies
        if (!movies.isEmpty()) {
            val movieAdapter = MovieAdapter(this@SearchActivity)
            movieAdapter.setData(movies)
            fragments.add(createRvFragment(movieAdapter, MOVIES))
        }

        val users = payload.users
        if (!users.isEmpty()) {
            val userAdapter = UserAdapter(this@SearchActivity)
            userAdapter.setData(users)
            fragments.add(createRvFragment(userAdapter, USERS))
        }

        setupViewPager(fragments)
    }

    private fun setupViewPager(fragments: ArrayList<RecyclerViewFragment>) {
        search_viewpager.adapter = ViewPagerAdapter(fragments, supportFragmentManager)
        search_viewpager.offscreenPageLimit = fragments.size
        search_viewpager.pageMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2f, resources.displayMetrics).toInt()
        search_tabs.setViewPager(search_viewpager)
        search_tabs.setAllCaps(true)
    }

    private fun <T : RecyclerView.Adapter<*>> createRvFragment(adapter: T, title: String): RecyclerViewFragment {
        val fragment = RecyclerViewFragment.newInstance(adapter, title)
        fragments.put(title, fragment)
        return fragment
    }

    override fun onMovieClick(view: View, movie: Movie) {
//        val id = (fragments[MOVIES]!!.adapter as MovieAdapter).getMovieId(position)
//        startActivity(MovieActivity.createIntent(this, id))
    }

    override fun onFavoriteClick(view: View, movie: Movie) {
//        App.apiService.addToFavorites(SessionController.user!!.token, movie.id)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({  }, Throwable::printStackTrace)
    }

    override fun onWatchlistClick(view: View, movie: Movie) {
//        App.apiService.addToWatchlist(SessionController.user!!.token, movie.id)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({  }, Throwable::printStackTrace)
    }

    override fun onCommentClick(view: View, movie: Movie) {
//        startActivity(CommentsActivity.createIntent(this, movie.id))
    }

    override fun onUserClick(view: View, position: Int, user: User) {
        if (view.id == R.id.btn_follow) {

            view.isEnabled = false
            view.alpha = 0.5f

//            App.apiService.follow(SessionController.user!!.token, user.id)
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .map { it.follow }
//                    .subscribe(
//                            {
//                                view.isEnabled = true
//                                view.alpha = 1f
//                                view.isSelected = it
//                                user.followedByMe = it
//                            },
//                            {
//                                view.isEnabled = true
//                                view.alpha = 1f
//                            }
//                    )
        } else {
            TODO("PROFILE atcivity")
            //startActivity(ProfileActivity.createIntent(this, user))
        }
    }

}
