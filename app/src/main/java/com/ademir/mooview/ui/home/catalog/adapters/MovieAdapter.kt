package com.ademir.mooview.home.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.ademir.mooview.R
import com.ademir.mooview.commons.Constants
import com.ademir.mooview.commons.inflate
import com.ademir.mooview.commons.load
import com.ademir.mooview.data.models.Movie
import kotlinx.android.synthetic.main.row_movie.view.*

/**
 * Created by ademir on 27/05/17.
 */
class MovieAdapter(val listener: OnMovieClickListener? = null) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dataSet = ArrayList<Movie>(10)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as MovieViewHolder
        holder.bind(dataSet[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.MovieViewHolder {
        return MovieViewHolder(parent.inflate(R.layout.row_movie), listener)
    }

    override fun getItemCount() = dataSet.size

    fun getMovieId(position: Int) = dataSet[position].id

    fun setData(dataSet: List<Movie>) {
        this.dataSet.clear()
        this.dataSet.addAll(dataSet)
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(view: View, var listener: OnMovieClickListener?) : RecyclerView.ViewHolder(view) {

        fun bind(movie: Movie) = with(itemView) {

            setOnClickListener { listener?.onMovieClick(it, adapterPosition) }

            with(movie) {
                iv_image.load(Constants.Urls.TMDB_POSTER + posterPath!!, R.drawable.ic_image_gray_24dp)
                tv_title.text = title

                if (categories!!.isNotEmpty()) {
                    val textCategories = StringBuilder()
                    categories!!.forEach { textCategories.append(it.name).append(", ") }
                    val len = textCategories.length
                    textCategories.delete(len - 2, len - 1)
                    tv_categories.text = textCategories.toString()
                }

                tv_rating.text = rating.toString()
                tv_overview.text = overview
                tv_year.text = year

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

        }

    }

    interface OnMovieClickListener {
        fun onMovieClick(view: View, position: Int)
        fun onFavoriteClick(view: View, movie: Movie)
        fun onCommentClick(view: View, movie: Movie)
        fun onWatchlistClick(view: View, movie: Movie)
    }

}