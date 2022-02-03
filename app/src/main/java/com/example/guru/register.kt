package com.example.guru

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*

class register : CalendarView() {

    lateinit var btn_register1: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btn_register1 = findViewById(R.id.btn_register1)


        btn_register1.setOnClickListener {


        }
    }
}
