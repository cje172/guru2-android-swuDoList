package com.example.guru

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CategoryDBHelper(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "CREATE TABLE category (name text)"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS category")
        onCreate(db)
    }

    fun insertCategory(categoryName: String) {
        val values = ContentValues()
        values.put("name", categoryName)
        writableDatabase.insert("category", null, values)
        writableDatabase.close()
    }

    fun selectCategory(): ArrayList<String> {
        val list = ArrayList<String>()
        val selectAll = "SELECT * FROM category"
        val cursor = readableDatabase.rawQuery(selectAll, null)

        while (cursor.moveToNext()) {
            val name = cursor.getString(cursor.getColumnIndex("name"))
            list.add(name.toString())
        }
        cursor.close()
        readableDatabase.close()

        return list
    }

    fun deleteCategory(categoryName: String) {
        val delete = "DELETE FROM category WHERE name = '$categoryName';"
        writableDatabase.execSQL(delete)
        writableDatabase.close()
    }

    fun updateCategory(oldName: String, newName: String) {
        val values = ContentValues()
        values.put("name", newName)

        val selectionArgs = arrayOf<String>(oldName)
        writableDatabase.update("category", values, "name = ?", selectionArgs)
        writableDatabase.close()
    }
}
