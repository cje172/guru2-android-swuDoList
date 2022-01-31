package com.example.guru

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.ListView
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import com.example.guru.ThemeConstant.appColor
import com.example.guru.ThemeConstant.appTheme
import com.example.guru.ThemeConstant.app_preferences
import com.example.guru.ThemeConstant.constant
import com.example.guru.ThemeConstant.editor
import com.example.guru.ThemeConstant.sharedPreferences
import com.example.guru.ThemeConstant.themeColor
import com.example.guru.ThemeConstant.themeMethods
import com.google.android.material.navigation.NavigationView
import petrov.kristiyan.colorpicker.ColorPicker
import java.text.SimpleDateFormat
import java.util.ArrayList

@Suppress("DEPRECATION")
open class CalendarView : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var calendarView: CalendarView
    lateinit var listView: ListView

    lateinit var drawerLayout: DrawerLayout
    lateinit var drawerView: View

    // 메뉴 오픈 버튼 구현 lateinit var btn_menu_open : Button
    lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 테마 변경 설정
        app_preferences = PreferenceManager.getDefaultSharedPreferences(this)
        appColor = app_preferences.getInt("color", 0)
        appTheme = app_preferences.getInt("theme", 0)
        themeColor = appColor
        constant?.color = appColor

        when {
            themeColor == 0 -> setTheme(ThemeConstant.theme)
            appTheme == 0 -> setTheme(ThemeConstant.theme)
            else -> setTheme(appTheme)
        }

        themeMethods = ThemeMethods()
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        editor = sharedPreferences.edit()

        setContentView(R.layout.activity_calendar_view)

        calendarView = findViewById(R.id.calendarView)
        listView = findViewById(R.id.listView)

        drawerLayout = findViewById(R.id.cal_drawer_layout)
        drawerView = findViewById(R.id.drawer_menu)
        // 메뉴 오픈 버튼 연결 btn_menu_open = findViewById(R.id.   )

        navigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        // 메뉴 오픈 버튼 클릭 시
        //btn_menu_open.setOnClickListener {
        //    drawerLayout.openDrawer(drawerView)
        //}

        // 달력 최소 날짜
        calendarView.minDate = SimpleDateFormat("yyyyMMdd").parse("20220101").time
        // 달력 최대 날짜
        calendarView.maxDate = SimpleDateFormat("yyyyMMdd").parse("20221231").time
        // 선택 날짜 일정 출력
        calendarView.setOnDateChangeListener { calendarView, year, month, day ->
            // 리스트뷰에 일정 출력
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_list, menu)
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.url_main -> {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.swu.ac.kr/"))
                startActivity(Intent.createChooser(intent, "Browser"))
            }
            R.id.url_lib -> {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://lib.swu.ac.kr/"))
                startActivity(Intent.createChooser(intent, "Browser"))
            }
            R.id.url_info -> {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://swis.swu.ac.kr/"))
                startActivity(Intent.createChooser(intent, "Browser"))
            }
            R.id.url_eport -> {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://eport.swu.ac.kr/"))
                startActivity(Intent.createChooser(intent, "Browser"))
            }
            R.id.action_theme -> {
                openColorPicker()
            }
            R.id.action_category -> {
                val intent = Intent(this, CategoryListView::class.java)
                startActivity(intent)
            }
            R.id.action_logout -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }


    // 테마 변경 ColorPicker Dialog
    private fun openColorPicker() {
        val colorPicker = ColorPicker(this)
        colorPicker.setTitle("변경할 색을 고르세요")
        val colors = ArrayList<String>()

        colors.add("#9E1A20")
        colors.add("#101077")
        colors.add("#FFB300")
        colors.add("#205723")
        colors.add("#000000")

        colorPicker.setColors(colors)
            .setColumns(5)
            .setRoundColorButton(true)
            .setOnChooseColorListener(object : ColorPicker.OnChooseColorListener {
                override fun onChooseColor(position: Int, color: Int) {
                    // OK 버튼 클릭 시
                    ThemeConstant.color = color
                    themeMethods?.setColorTheme()

                    editor.putInt("color", color)
                    editor.putInt("theme", ThemeConstant.theme)
                    editor.commit()

                    recreate()

                    Toast.makeText(applicationContext, "테마 변경이 완료되었습니다", Toast.LENGTH_SHORT).show()
                }

                override fun onCancel() {
                    // 취소 버튼 클릭 시
                }
            }).show()
    }
}