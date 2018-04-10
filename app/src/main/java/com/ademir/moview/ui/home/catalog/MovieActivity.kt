package com.ademir.moview.home

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ademir.moview.R
import com.ademir.moview.ui.comments.CommentsActivity
import com.ademir.moview.commons.Constants
import com.ademir.moview.commons.load
import com.ademir.moview.commons.releaseDateFormatted
import com.ademir.moview.data.models.Movie
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieActivity : AppCompatActivity() {

    private val movieId by lazy { intent.getIntExtra(Constants.EXTRA_MOVIE_ID, -1) }

    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        setSupportActionBar(movie_detail_toolbar)

        tv_comments.setOnClickListener { startActivity(CommentsActivity.createIntent(this, movieId)) }

        getMovieDetails()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }

    fun getMovieDetails() {
//        disposable = App.apiService!!.getMovieDetails(SessionController.user!!.token, movieId)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        { setMovie(it) },
//                        {
//                            Snackbar.make(movie_details_containter, "Failed to load...", Snackbar.LENGTH_INDEFINITE)
//                                    .setAction(R.string.text_try_again) { getMovieDetails() }
//                                    .show()
//                        }
//                )
    }

    private fun setMovie(movie: Movie) = with(movie) {
        movie_detail_backdrop.load(Constants.Urls.TMDB_BACKDROP + backdropPath, R.drawable.ic_image_gray_24dp)
        movie_poster_image.load(Constants.Urls.TMDB_POSTER + posterPath, R.drawable.ic_image_gray_24dp)
        movie_detail_toolbar.title = title
        movie_detail_release_date.text = releaseDateFormatted(releaseDate)

        if (runtime > 0) {
            movie_detail_runtime.text = String.format("%02dmin", runtime)
            movie_detail_runtime.visibility = View.VISIBLE
        } else {
            movie_detail_runtime.visibility = View.INVISIBLE
        }

//        movie_detail_rating.text = getRating()
//        movie_detail_overview.text = overview
//        movie_detail_categories.text = getCategories()
//        tv_comments.text = String.format("%d %s", followingComments, getString(R.string.text_comments_of_following))
//        tv_budget.text = getBudget()
//        tv_revenue.text = getRevenue()
    }

    companion object {

        fun createIntent(context: Context, movieId: Int): Intent {
            val intent = Intent(context, MovieActivity::class.java)
            intent.putExtra(Constants.EXTRA_MOVIE_ID, movieId)
            return intent
        }
    }


}
