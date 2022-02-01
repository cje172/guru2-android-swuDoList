package com.example.guru

import android.graphics.Color

class ThemeMethods {
    fun setColorTheme() {
        when (ThemeConstant.color) {
            Color.parseColor("#9E1A20") -> ThemeConstant.theme = R.style.Theme_Guru
            Color.parseColor("#101077") -> ThemeConstant.theme = R.style.Theme_Blue
            Color.parseColor("#FFB300") -> ThemeConstant.theme = R.style.Theme_Yellow
            Color.parseColor("#205723") -> ThemeConstant.theme = R.style.Theme_Green
            Color.parseColor("#000000") -> ThemeConstant.theme = R.style.Theme_Black
            else -> ThemeConstant.theme = R.style.Theme_Guru
        }
    }
}