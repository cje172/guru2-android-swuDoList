package com.example.guru

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class Login : AppCompatActivity() {

    lateinit var btn_register: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_register = findViewById(R.id.btn_register)

        btn_register.setOnClickListener {
            var intent = Intent(this, register:: class.java)
            startActivity(intent)
            Toast.makeText(this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}