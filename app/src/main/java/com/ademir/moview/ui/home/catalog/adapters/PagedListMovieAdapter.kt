package com.ademir.moview.ui.home.catalog.adapters

import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.ademir.moview.R
import com.ademir.moview.commons.inflate
import com.ademir.moview.data.models.Movie
import com.ademir.moview.home.adapters.MovieAdapter

class PagedListMovieAdapter(val listener: MovieAdapter.OnMovieClickListener)
    : PagedListAdapter<Movie, androidx.recyclerview.widget.RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): androidx.recyclerview.widget.RecyclerView.ViewHolder {
        return MovieAdapter.MovieViewHolder(parent.inflate(R.layout.row_movie), listener)
    }

    override fun onBindViewHolder(holder: androidx.recyclerview.widget.RecyclerView.ViewHolder, position: Int) {
        (holder as MovieAdapter.MovieViewHolder).bind(getItem(position))
    }

    companion object {

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }

}