package com.example.guru

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.EditText

class EditCategory : AppCompatActivity() {

    lateinit var addButton: Button
    lateinit var modifyButton: Button
    lateinit var deleteButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_category)

        addButton = findViewById<Button>(R.id.addButton)
        modifyButton = findViewById<Button>(R.id.modifyButton)
        deleteButton = findViewById<Button>(R.id.deleteButton)


        // 추가 버튼 클릭 시
        addButton.setOnClickListener(View.OnClickListener {
            showDialog()

            // 아이템 추가
            //list.add(categoryName)

            // listview 갱신
            //adapter.notifyDataSetChanged()
        })
        // 수정 버튼 클릭 시

        // 삭제 버튼 클릭 시
    }

    fun showDialog() {
        val builder: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("카테고리 추가")

        // Set up the input
        val input = EditText(this)
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setHint("추가할 카테고리 이름 입력")
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)

        // Set up the buttons
        builder.setPositiveButton(
            "OK",
            DialogInterface.OnClickListener { dialog, which ->
                // Here you get get input text from the Edittext
                var categoryName = input.text.toString()
            })
        builder.setNegativeButton(
            "Cancel",
            DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        builder.show()

    }

}