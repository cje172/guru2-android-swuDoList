package com.example.guru

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView

open class CategoryListView : CalendarView() {
    lateinit var categoryTextView: TextView
    lateinit var categoryListView: ListView
    lateinit var categoryButton: Button

    lateinit var categoryHelper : CategoryDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_view)

        categoryTextView = findViewById(R.id.categoryTextView)
        categoryListView = findViewById(R.id.categoryListView)
        categoryButton = findViewById(R.id.categoryButton)

        // 리스트뷰에 어댑터 연결
        var list = arrayListOf<String>()
        var adapter = ListViewAdapter(this, list)

        categoryHelper = CategoryDBHelper(this, "CategoryData.db", null, 1)
        adapter.list.addAll(categoryHelper.selectCategory())
        adapter.helper = categoryHelper

        categoryListView.adapter = adapter

        categoryButton.setOnClickListener {
            // 카테고리 수정 화면으로 이동
            val intent = Intent(this, EditCategory::class.java)
            startActivity(intent)
        }
    }
}