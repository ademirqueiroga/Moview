package com.ademir.moview.home.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.ademir.moview.R
import com.ademir.moview.commons.Constants
import com.ademir.moview.commons.inflate
import com.ademir.moview.commons.load
import com.ademir.moview.data.models.Movie
import kotlinx.android.synthetic.main.row_movie.view.*

/**
 * Created by ademir on 27/05/17.
 */
class MovieAdapter(val listener: OnMovieClickListener? = null) : androidx.recyclerview.widget.RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder>() {

    private val dataSet = ArrayList<Movie>(10)

    override fun onBindViewHolder(holder: androidx.recyclerview.widget.RecyclerView.ViewHolder, position: Int) {
        holder as MovieViewHolder
        holder.bind(dataSet[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.MovieViewHolder {
        return MovieViewHolder(parent.inflate(R.layout.row_movie), listener)
    }

    override fun getItemCount() = dataSet.size

    override fun getItemViewType(position: Int): Int {
        return R.layout.row_movie
    }

    fun getMovieId(position: Int) = dataSet[position].id

    fun setData(dataSet: List<Movie>) {
        this.dataSet.clear()
        this.dataSet.addAll(dataSet)
        notifyDataSetChanged()
    }

    class MovieViewHolder(view: View, var listener: OnMovieClickListener?) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {

        fun bind(movie: Movie?) = with(itemView) {
            if (movie != null) {
                with(movie) {
                    iv_image.load(Constants.Urls.TMDB_POSTER + posterPath)
                    tv_title.text = title

                    if (genres.isNotEmpty()) {
                        val textCategories = StringBuilder()
                        genres.forEach { textCategories.append(it.name).append(", ") }
                        val len = textCategories.length
                        textCategories.delete(len - 2, len - 1)
                        tv_categories.text = textCategories.toString()
                    }

                    tv_rating.text = voteAverage.toString()
                    tv_overview.text = overview
                    tv_year.text = releaseDate.substring(0, 4)

                    setOnClickListener { listener?.onMovieClick(it, movie) }
                    btn_comment.setOnClickListener { listener?.onCommentClick(it, movie) }
                    btn_favorite.setOnClickListener {
                        it.isSelected = !it.isSelected
                        listener?.onFavoriteClick(it, movie)
                    }

                    btn_watchlist.setOnClickListener {
                        it.isSelected = !it.isSelected
                        listener?.onWatchlistClick(it, movie)
                    }

                }
            } else {
                clear()
            }

        }

        private fun clear() {
            // TODO
        }

    }

    interface OnMovieClickListener {
        fun onMovieClick(view: View, movie: Movie)
        fun onFavoriteClick(view: View, movie: Movie)
        fun onCommentClick(view: View, movie: Movie)
        fun onWatchlistClick(view: View, movie: Movie)
    }

}