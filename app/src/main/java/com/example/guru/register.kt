package com.example.guru

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class register : CalendarView() {

    lateinit var dbManager: DBManager
    override lateinit var sqlitedb: SQLiteDatabase

    lateinit var btn_register1: Button
    lateinit var et_id: EditText
    lateinit var et_pass: EditText
    lateinit var et_name: EditText
    lateinit var et_hak: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btn_register1 = findViewById(R.id.btn_register1)
        et_id = findViewById(R.id.et_id)
        et_pass = findViewById(R.id.et_pass)
        et_name = findViewById(R.id.et_name)
        et_hak = findViewById(R.id.et_hak)

        dbManager = DBManager(this, "person", null, 1)


        btn_register1.setOnClickListener {

            var str_id: String = et_id.text.toString()
            var str_pass: String = et_pass.text.toString()
            var str_name: String = et_name.text.toString()
            var str_hak: String = et_hak.text.toString()

                Toast.makeText(this, "회원가입 되었습니다.", Toast.LENGTH_SHORT).show()
                var intent = Intent(this, Login::class.java)
                intent.putExtra("intent_name", str_name)
                startActivity(intent)

                sqlitedb = dbManager.writableDatabase
                sqlitedb.execSQL("INSERT INTO person VALUES ('" + str_id + "', '" + str_pass + "', '" + str_name + "', '" + str_hak + "')")
                sqlitedb.close()

            }
        }
    }


