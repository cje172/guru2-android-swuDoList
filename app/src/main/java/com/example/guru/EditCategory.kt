package com.example.guru

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class EditCategory : CategoryListView() {

    lateinit var addButton: Button
    lateinit var categoryEditText: EditText
    lateinit var categoryName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_category)

        // 뒤로가기 버튼
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        addButton = findViewById<Button>(R.id.addButton)
        categoryEditText = findViewById<EditText>(R.id.categoryEditText)
        categoryListView = findViewById(R.id.categoryListView)


        // 리스트뷰에 어댑터 연결
        var list = arrayListOf<String>("수업", "동아리") // 테스트
        var adapter = ListViewAdapter(this, list)
        categoryListView.adapter = adapter

        // 카테고리 추가 버튼 클릭 시
        addButton.setOnClickListener(View.OnClickListener {
            categoryName = categoryEditText.text.toString()

            if (categoryName.isEmpty()) {
                Toast.makeText(applicationContext, "카테고리 이름을 입력하세요", Toast.LENGTH_SHORT).show()
            } else {
                // 아이템 추가
                list.add(categoryName)
                // listview 갱신
                adapter.notifyDataSetChanged()

                categoryEditText.setText("")
            }
        })
    }
}