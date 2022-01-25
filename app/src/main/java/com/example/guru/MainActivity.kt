package com.example.guru

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var btn_ex: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_ex = findViewById(R.id.btn_ex)

        btn_ex.setOnClickListener {
            var intent = Intent(this, Login:: class.java)
            startActivity(intent)
        }
    }
}
