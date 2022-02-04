package com.example.guru

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBManager(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase?) {

        db!!.execSQL("CREATE TABLE person (id text, pass text, name text, hak INTEGER)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, odlVersion: Int, newVersion: Int) {

    }
}