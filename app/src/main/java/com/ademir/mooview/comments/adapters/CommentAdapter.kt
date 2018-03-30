package com.ademir.mooview.comments.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.ademir.mooview.R
import com.ademir.mooview.commons.inflate
import com.ademir.mooview.commons.isToday
import com.ademir.mooview.model.Comment
import kotlinx.android.synthetic.main.row_comment.view.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by ademir on 28/05/17.
 */
class CommentAdapter(val context: Context) : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    private val dataSet = ArrayList<Comment>()

    override fun onBindViewHolder(holder: CommentAdapter.CommentViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentAdapter.CommentViewHolder {
        return CommentViewHolder(parent.inflate(R.layout.row_comment))
    }

    override fun getItemCount() = dataSet.size

    fun setData(dataSet: List<Comment>) {
        this.dataSet.clear()
        this.dataSet.addAll(dataSet)
        notifyDataSetChanged()
    }

    fun addAll(dataSet: List<Comment>) {
        val oldSize = dataSet.size
        this.dataSet.addAll(dataSet)
        notifyItemRangeInserted(oldSize, this.dataSet.size - 1)
    }

    fun add(comment: Comment) {
        dataSet.add(0, comment)
        notifyItemInserted(0)
    }

    class CommentViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        companion object {
            val dateFormatter = SimpleDateFormat("HH:mm", Locale.getDefault())
            var calendar: Calendar = Calendar.getInstance()
        }

        fun bind(comment: Comment) = with(itemView) {
            with(comment) {
                //todo iv_picture.load()
                tv_username.text = String.format("@%s", username)
                tv_content.text = content
                tv_about.text = String.format("about %s", movieTitle)
                tv_likes.text = String.format("%d likes", likes)

                if (isToday(createdAt)) {
                    tv_date.text = dateFormatter.format(Date(createdAt))
                } else {
                    calendar.timeInMillis = createdAt
                    val day = calendar.get(Calendar.DAY_OF_MONTH)
                    val month = calendar.get(Calendar.MONTH)
                    val year = calendar.get(Calendar.YEAR)
                    tv_date.text = String.format(Locale.getDefault(), "%02d/%02d/%d", day, month, year)
                }

            }
        }


    }

}