package com.example.guru

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog


class EditListViewAdapter(val context: Context, val list: ArrayList<String>) : BaseAdapter() {

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

        val view: View = LayoutInflater.from(context).inflate(R.layout.edit_list_view_item, null)

        val name = view.findViewById<TextView>(R.id.itemTextId)
        val delBtn = view.findViewById<ImageButton>(R.id.delBtn)
        val editBtn = view.findViewById<ImageButton>(R.id.editBtn)

        delBtn.tag = p0
        editBtn.tag = p0

        name.text = list[p0]

        // 수정 버튼 클릭 시
        editBtn.setOnClickListener {
            var selectedList = list.get(it.tag as Int)

            // 카테고리 수정 Dialog
            val builder = AlertDialog.Builder(context)

            val customView = LayoutInflater.from(this.context)
                .inflate(R.layout.dialog_layout, p2 as ViewGroup, false)

            val input = customView.findViewById<EditText>(R.id.name)
            val dialogTitle: TextView = customView.findViewById(R.id.dialogTitle)

            input.setText(selectedList)
            input.setSelection(input.length())
            dialogTitle.text = "카테고리 수정"

            builder.setView(customView)

            // Dialog 수정 버튼 클릭 시
            builder.setPositiveButton("수정") { dialog, which ->
                if (input.text.toString().isEmpty()) {
                    Toast.makeText(context, "카테고리 이름을 입력하세요", Toast.LENGTH_SHORT).show()
                } else {
                    var cursor = it.tag as Int
                    var oldName = list[cursor]

                    list[it.tag as Int] = input.text.toString()

                    helper!!.updateCategory(oldName, list.get(cursor))
                    notifyDataSetChanged()
                    Toast.makeText(context, "카테고리 수정이 완료되었습니다", Toast.LENGTH_SHORT).show()
                }
            }
            // Dialog 취소 버튼 클릭 시
            builder.setNegativeButton("취소") { dialog, which -> dialog.cancel() }

            builder.show()
        }

        // 삭제 버튼 클릭 시
        delBtn.setOnClickListener {
            val selectedList = list.get(it.tag as Int)

            var cursor = it.tag as Int
            helper!!.deleteCategory(list.get(cursor))
            list.remove(selectedList)
            notifyDataSetChanged()
            Toast.makeText(context, "카테고리가 삭제되었습니다", Toast.LENGTH_SHORT).show()
        }
        return view
    }

}