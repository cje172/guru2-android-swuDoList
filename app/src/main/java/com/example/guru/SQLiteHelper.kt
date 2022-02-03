package com.example.guru

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int)
    : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase?) {
        val create = "CREATE TABLE data (selectedCategory text, content text)"
        db!!.execSQL(create)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS data")
        onCreate(db)
    }

    // insert 메소드 : todo 추가
    fun insertTodo(data: Data) {
        val values = ContentValues()
        values.put("selectedCategory", data.selectedCategory)
        values.put("content", data.content)
        writableDatabase.insert("data", null, values)
        writableDatabase.close()
    }

    // select 메소드
    fun selectTodo() : MutableList<Data> {
        val list = mutableListOf<Data>()
        val selectAll = "SELECT * FROM data"
        val cursor = readableDatabase.rawQuery(selectAll, null)

        while (cursor.moveToNext()) {
            val selectedCategory = cursor.getString(cursor.getColumnIndex("selectedCategory"))
            val content = cursor.getString(cursor.getColumnIndex("content"))

            list.add(Data(selectedCategory, content))
        }
        cursor.close()
        readableDatabase.close()

        return list
    }

    // delete 메소드 : todo 삭제
    fun deleteData(data: Data) {
        val delete = "DELETE FROM data WHERE content = '" + data.content + "';"
        writableDatabase.execSQL(delete)
        writableDatabase.close()
    }
}