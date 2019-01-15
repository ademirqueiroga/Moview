package com.ademir.moview.search.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ademir.moview.R
import com.ademir.moview.commons.prepare
import kotlinx.android.synthetic.main.fragment_recyclerview.*

/**
 * Created by ademir on 28/05/17.
 */
class RecyclerViewFragment : androidx.fragment.app.Fragment() {

    lateinit var adapter: androidx.recyclerview.widget.RecyclerView.Adapter<*>
    lateinit var title: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view.prepare(adapter, androidx.recyclerview.widget.LinearLayoutManager(context))
    }

    companion object {
        fun newInstance(adapter: androidx.recyclerview.widget.RecyclerView.Adapter<*>, title: String = ""): RecyclerViewFragment {
            val frag = RecyclerViewFragment()
            frag.adapter = adapter
            frag.title = title
            return frag
        }
    }

}