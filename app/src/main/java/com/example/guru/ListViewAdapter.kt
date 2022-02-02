package com.example.guru

import android.content.Context
import android.content.DialogInterface
import android.content.pm.ActivityInfo
import android.media.Image
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class ListViewAdapter(val context: Context, val list: ArrayList<String>) : BaseAdapter() {

    var helper: CategoryDBHelper? = null

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

        val view: View = LayoutInflater.from(context).inflate(R.layout.list_view_item, null)

        val name = view.findViewById<TextView>(R.id.itemTextId)
        name.text = list[p0]

        return view
    }

}