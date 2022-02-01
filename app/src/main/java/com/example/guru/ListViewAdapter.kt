package com.example.guru

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.TextView

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

        val view: View = LayoutInflater.from(context).inflate(R.layout.edit_list_view_item, null)

        val name = view.findViewById<TextView>(R.id.itemTextId)
        val delBtn = view.findViewById<ImageButton>(R.id.delBtn)
        val editBtn = view.findViewById<ImageButton>(R.id.editBtn)

        delBtn.tag = p0
        name.text = list[p0]

        // 수정 버튼 클릭 시
        editBtn.setOnClickListener{
            //val selectedList = list.get(it.tag as Int)

            notifyDataSetChanged()
        }

        // 삭제 버튼 클릭 시
        delBtn.setOnClickListener {
            val selectedList = list.get(it.tag as Int)
            list.remove(selectedList)
            notifyDataSetChanged()
        }
        return view
    }

}