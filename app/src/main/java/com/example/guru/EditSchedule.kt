package com.example.guru

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_edit_schedule.*
import java.io.File
import java.lang.Exception

class EditSchedule : CalendarView() {
    lateinit var add_todo_btn : ImageButton
    lateinit var add_todo_text : EditText

    lateinit var sqLiteHelper : SQLiteHelper


    lateinit var categoryHelper : CategoryDBHelper
    lateinit var selectedCategory : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_schedule)

        add_todo_btn = findViewById(R.id.add_todo_btn)
        add_todo_text = findViewById(R.id.add_todo)

        sqLiteHelper = SQLiteHelper(this, "TodoData", null, 1)

        val adapter = EditScheduleAdapter()
        adapter.listData.addAll(sqLiteHelper.selectTodo())
        adapter.helper = sqLiteHelper

        recyclerTodo.adapter = adapter
        recyclerTodo.layoutManager = LinearLayoutManager(this)


        // 카테고리 - 스피너 연결
        var categoryList = arrayListOf<String>()
        categoryHelper = CategoryDBHelper(this, "CategoryData.db", null, 1)
        categoryList.addAll(categoryHelper.selectCategory())

        var spinnerAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, categoryList)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, View: View?, position: Int, id: Long) {
                selectedCategory = spinner.selectedItem.toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }

        // 접근하려는 폴더 경로
        var myDirPath = File("/sdcard/schedule")
        // 폴더가 없을 경우, 폴더 생성
        if (myDirPath.exists().not()) {
            myDirPath.mkdir()
        }

        // 추가 버튼 눌렀을 때
        add_todo_btn.setOnClickListener {
            var year = intent.getIntExtra("year", 0).toString()
            var month = intent.getIntExtra("month", 0).toString()
            var day = intent.getIntExtra("day", 0).toString()

            // 파일 이름
            var fileName = year + "_" + month + "_" + day + ".txt"

            if (add_todo_text.text.toString().isNotEmpty()) {
                val todo = Data(selectedCategory, add_todo_text.text.toString())
                sqLiteHelper.insertTodo(todo)

                try {
                    var outFs = openFileOutput(fileName, Context.MODE_PRIVATE)
                    outFs.write(("[" + selectedCategory + "] " + add_todo_text.text.toString()).toByteArray())
                    outFs.close()
                } catch (e: Exception) {
                }
            }
            adapter.listData.clear()
            adapter.listData.addAll(sqLiteHelper.selectTodo())

            adapter.notifyDataSetChanged()
            add_todo_text.setText("")
        }
    }
}