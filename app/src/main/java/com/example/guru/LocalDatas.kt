package com.example.guru

import android.provider.BaseColumns

object LocalDatas {
    object userData : BaseColumns {
        const val TABLE_NAME = "users"
        const val COLUMN_NAME_ID = "id"
        const val COLUMN_NAME_HAK = "hak"
        const val COLUMN_NAME_NAME = "name"
        const val COLUMN_NAME_PASSWORD = "password"
    }
}