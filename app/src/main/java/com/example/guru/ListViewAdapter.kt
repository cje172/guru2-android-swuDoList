package com.example.guru

import android.content.Context
import android.content.DialogInterface
import android.media.Image
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

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
        editBtn.tag = p0

        name.text = list[p0]

        // 수정 버튼 클릭 시
        editBtn.setOnClickListener{
            var selectedList = list.get(it.tag as Int)

            // 카테고리 수정 Dialog
            val builder: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(context)
            builder.setTitle("카테고리 수정")

            val input = EditText(context)
            input.setText(selectedList)
            input.inputType = InputType.TYPE_CLASS_TEXT
            builder.setView(input)

            builder.setPositiveButton( // Dialog 수정 버튼 클릭 시
                "수정",
               DialogInterface.OnClickListener { dialog, which ->
                    if (input.text.toString().isEmpty()) {

                        Toast.makeText(context, "카테고리 이름을 입력하세요", Toast.LENGTH_SHORT).show()
                    } else {
                        list[it.tag as Int] = input.text.toString()
                        notifyDataSetChanged()
                        Toast.makeText(context, "카테고리 수정이 완료되었습니다", Toast.LENGTH_SHORT).show()
                    }
                })
            builder.setNegativeButton(  // Dialog 취소 버튼 클릭 시
                "취소",
                DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

            builder.show()
        }

        // 삭제 버튼 클릭 시
        delBtn.setOnClickListener {
            val selectedList = list.get(it.tag as Int)
            list.remove(selectedList)
            notifyDataSetChanged()
            Toast.makeText(context, "카테고리가 삭제되었습니다", Toast.LENGTH_SHORT).show()
        }
        return view
    }

}