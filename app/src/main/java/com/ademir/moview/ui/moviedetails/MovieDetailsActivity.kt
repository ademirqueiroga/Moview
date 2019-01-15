package com.ademir.moview.ui.moviedetails

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import com.ademir.moview.MoviewApplication
import com.ademir.moview.R
import com.ademir.moview.commons.Constants
import com.ademir.moview.commons.load
import com.ademir.moview.commons.releaseDateFormatted
import com.ademir.moview.data.models.Movie
import com.ademir.moview.data.models.Video
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_movie_details.*
import java.text.DecimalFormat
import java.util.*
import javax.inject.Inject

class MovieDetailsActivity : AppCompatActivity(), MovieDetailsContract.View {

    private val movie by lazy { intent.getParcelableExtra<Movie>(Movie.EXTRA_MOVIE) }

    private var disposable: Disposable? = null

    @Inject
    lateinit var presenter: MovieDetailsPresenter

    val progressDialog by lazy { ProgressDialog(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        setSupportActionBar(movie_detail_toolbar)

        MoviewApplication.get(this).applicationComponent.inject(this)
        presenter.bindView(this)
        setMovie(movie, null)

        tv_comments.setOnClickListener {  }

        presenter.loadMovie(movie.id)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }

    override fun setMovie(movie: Movie, video: Video?) {
        with(movie) {
            movie_detail_backdrop.load(Constants.Urls.TMDB_BACKDROP + backdropPath, R.drawable.ic_image_gray_24dp)
            movie_poster_image.load(Constants.Urls.TMDB_POSTER + posterPath, R.drawable.ic_image_gray_24dp)
            supportActionBar?.title = title
            movie_detail_toolbar.title = title
            movie_detail_release_date.text = releaseDateFormatted(releaseDate)

            if (runtime > 0) {
                movie_detail_runtime.text = String.format("%02dmin", runtime)
                movie_detail_runtime.visibility = View.VISIBLE
            } else {
                movie_detail_runtime.visibility = View.INVISIBLE
            }

            movie_detail_rating.text = voteAverage.toString()
            movie_detail_overview.text = overview
            val currencyFormatter = DecimalFormat.getCurrencyInstance(Locale.getDefault())
            tv_budget.text = currencyFormatter.format(budget)
            tv_revenue.text = currencyFormatter.format(revenue)
        }

        video_view.setVideoPath("http://www.html5videoplayer.net/videos/toystory.mp4")
        video_view.start()

    }

    override fun showError() {
        Snackbar.make(movie_details_containter, R.string.error_try_again, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.text_try_again) { presenter.loadMovie(movie.id) }
                .show()
    }

    override fun showLoader(show: Boolean) {
        if (show) {
            progressDialog.show()
        } else {
            progressDialog.dismiss()
        }
    }

    companion object {
        fun createIntent(context: Context, movie: Movie): Intent {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(Movie.EXTRA_MOVIE, movie)
            return intent
        }
    }


}
