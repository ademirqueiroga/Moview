package com.ademir.mooview.search.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ademir.mooview.R
import com.ademir.mooview.commons.prepare
import kotlinx.android.synthetic.main.fragment_recyclerview.*

/**
 * Created by ademir on 28/05/17.
 */
class RecyclerViewFragment : Fragment() {

    lateinit var adapter: RecyclerView.Adapter<*>
    lateinit var title: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view.prepare(adapter, LinearLayoutManager(context))
    }

    companion object {
        fun newInstance(adapter: RecyclerView.Adapter<*>, title: String = ""): RecyclerViewFragment {
            val frag = RecyclerViewFragment()
            frag.adapter = adapter
            frag.title = title
            return frag
        }
    }

}