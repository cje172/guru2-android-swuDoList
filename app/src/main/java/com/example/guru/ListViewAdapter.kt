package com.example.guru

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

class ListViewAdapter(val context: Context, val list: ArrayList<String>): BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(p0: Int): Any {
        return list[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        TODO("Not yet implemented")
    }
}