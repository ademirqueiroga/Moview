package com.ademir.moview.ui.home.catalog.adapters

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.ademir.moview.R
import com.ademir.moview.commons.inflate
import com.ademir.moview.data.models.Movie
import com.ademir.moview.home.adapters.MovieAdapter

class PagedListMovieAdapter(val listener: MovieAdapter.OnMovieClickListener)
    : PagedListAdapter<Movie, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieAdapter.MovieViewHolder(parent.inflate(R.layout.row_movie), listener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
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