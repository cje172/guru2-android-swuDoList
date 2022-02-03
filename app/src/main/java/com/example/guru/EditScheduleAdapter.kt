package com.example.guru

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_recycler.view.*


class EditScheduleAdapter : RecyclerView.Adapter<EditScheduleAdapter.Holder>() {
    var listData = ArrayList<Data>()
    var helper : SQLiteHelper? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditScheduleAdapter.Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
        return Holder(view).apply {
            // 삭제(휴지통) 버튼 클릭 시
            itemView.todoDelBtn.setOnClickListener {
                var cursor = adapterPosition

                helper!!.deleteData(listData.get(cursor))
                listData.remove(listData.get(cursor))
                notifyDataSetChanged()
            }
        }
    }

    override fun onBindViewHolder(holder: EditScheduleAdapter.Holder, position: Int) {
        val data : Data = listData.get(position)
        holder.setItem(data)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setItem(item : Data) {
            itemView.categoryText.text = item.selectedCategory.toString()
            itemView.todoText.text = item.content.toString()
        }
    }
}