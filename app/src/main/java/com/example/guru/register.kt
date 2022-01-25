package com.example.guru

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class register : AppCompatActivity() {

    lateinit var btn_register1: Button
    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_register)

    btn_register1 = findViewById(R.id.btn_register1)

    btn_register1.setOnClickListener {
        var intent = Intent(this, Login:: class.java)
        startActivity(intent)
    }
}
}