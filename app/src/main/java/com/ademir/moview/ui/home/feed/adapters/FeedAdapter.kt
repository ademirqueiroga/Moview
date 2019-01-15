package com.ademir.moview.home.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.ademir.moview.R
import com.ademir.moview.commons.Constants
import com.ademir.moview.commons.inflate
import com.ademir.moview.commons.load
import com.ademir.moview.model.FeedPost
import kotlinx.android.synthetic.main.row_feed.view.*

/**
 * Created by ademir on 27/05/17.
 */
class FeedAdapter: androidx.recyclerview.widget.RecyclerView.Adapter<FeedAdapter.FeedItemViewHolder>() {

    private val dataSet = ArrayList<FeedPost>()

    override fun onBindViewHolder(holder: FeedAdapter.FeedItemViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedItemViewHolder =
            FeedItemViewHolder(parent.inflate(R.layout.row_feed))

    override fun getItemCount() = dataSet.size

    fun setData(dataSet: List<FeedPost>) {
        this.dataSet.clear()
        this.dataSet.addAll(dataSet)
        notifyDataSetChanged()
    }

    class FeedItemViewHolder(view: View): androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        fun bind(post: FeedPost) = with(itemView){
            with(post) {
                tv_post_info.text = String.format("%s commented about:", user.name)
                tv_movie_title.text = movie.title
                tv_post_content.text = content
                iv_movie_poster.load(Constants.Urls.TMDB_POSTER + movie.posterPath!!, R.drawable.ic_image_gray_24dp)
            }
        }
    }

}