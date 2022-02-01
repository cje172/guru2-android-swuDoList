package com.example.guru

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class Login : AppCompatActivity() {

    lateinit var btn_register: Button
    lateinit var btn_login : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_register = findViewById(R.id.btn_register)
        btn_login = findViewById<Button>(R.id.btn_login)

        btn_register.setOnClickListener {
            var intent = Intent(this, register:: class.java)
            startActivity(intent)
        }

        btn_login.setOnClickListener {
            var intent = Intent(this, CalendarView:: class.java)
            startActivity(intent)
        }
    }
}