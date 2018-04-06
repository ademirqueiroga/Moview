package com.ademir.moview.search.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.ademir.mooview.R
import com.ademir.mooview.commons.inflate
import com.ademir.moview.model.User
import kotlinx.android.synthetic.main.row_user.view.*


class UserAdapter(val listener: UserAdapter.UserClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var dataSet = ArrayList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserViewHolder {
        return UserViewHolder(parent.inflate(R.layout.row_user), listener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as UserViewHolder
        holder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size

    fun setData(dataSet: List<User>) {
        this.dataSet.clear()
        this.dataSet.addAll(dataSet)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(itemView: View, private val listener: UserClickListener?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
            itemView.btn_follow.setOnClickListener(this)
        }

        fun bind(user: User) = with(itemView) {
            with(user) {
                //todo iv_picture.load()
                tv_username.text = String.format("@%s", username)
                tv_name.text = name
                btn_follow.isSelected = followedByMe
            }
        }

        override fun onClick(view: View) {
            val position = adapterPosition
            listener?.onUserClick(view, position, dataSet[position])
        }
    }

    interface UserClickListener {
        fun onUserClick(view: View, position: Int, user: User)
    }

}
