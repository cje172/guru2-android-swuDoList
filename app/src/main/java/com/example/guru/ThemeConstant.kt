package com.example.guru

import android.content.SharedPreferences
import android.graphics.Color
import java.io.Serializable

object ThemeConstant : Serializable {
    var color = Color.parseColor("#9E1A20")
    var theme: Int = R.style.Theme_Guru

    var appTheme = 0
    var themeColor = 0
    var appColor = 0
    var constant: ThemeConstant? = null
    var themeMethods: ThemeMethods? = null

    lateinit var sharedPreferences: SharedPreferences
    lateinit var app_preferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
}