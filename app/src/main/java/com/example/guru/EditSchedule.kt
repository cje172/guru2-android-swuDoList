package com.example.guru

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_edit_schedule.*

class EditSchedule : AppCompatActivity() {
    lateinit var add_todo_btn : Button
    lateinit var add_todo_text : EditText
    lateinit var helper : SQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_schedule)

        add_todo_btn = findViewById(R.id.add_todo_btn)
        add_todo_text = findViewById(R.id.add_todo)

        helper = SQLiteHelper(this, "TodoData", null, 1)

        val adapter = EditScheduleAdapter()
        adapter.listData.addAll(helper.selectTodo())
        adapter.helper = helper

        recyclerTodo.adapter = adapter
        recyclerTodo.layoutManager = LinearLayoutManager(this)

        // 추가 버튼 눌렀을 때
        add_todo_btn.setOnClickListener {
            if (add_todo_text.text.toString().isNotEmpty()) {
                val todo = Data(add_todo_text.text.toString())
                helper.insertTodo(todo)
            }
            adapter.listData.clear()
            adapter.listData.addAll(helper.selectTodo())

            adapter.notifyDataSetChanged()
            add_todo_text.setText("")
        }
    }
}