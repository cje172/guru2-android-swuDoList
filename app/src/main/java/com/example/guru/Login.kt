package com.example.guru

import android.content.Intent
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import android.widget.Button
import android.widget.EditText
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Body as Retrofit2HttpBody

class Login : CalendarView() {

    lateinit var btn_register: Button
    lateinit var btn_login: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_register = findViewById(R.id.btn_register)
        btn_login = findViewById<Button>(R.id.btn_login)

        btn_register.setOnClickListener {
            var intent = Intent(this, register::class.java)
            startActivity(intent)
        }

        btn_login.setOnClickListener {

        }
    }
}