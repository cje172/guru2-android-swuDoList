package com.example.guru

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.ListView
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import petrov.kristiyan.colorpicker.ColorPicker
import java.text.SimpleDateFormat
import java.util.ArrayList

class CalendarView : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var calendarView: CalendarView
    lateinit var listView: ListView

    lateinit var drawerLayout: DrawerLayout
    lateinit var drawerView: View

    // 메뉴 오픈 버튼 구현 lateinit var btn_menu_open : Button
    lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

    // 테마 변경 dialog
    fun openColorPicker() {
        val colorPicker = ColorPicker(this)
        colorPicker.setTitle("변경할 색을 고르세요")
        val colors = ArrayList<String>()

        val redInt: Int = Color.parseColor("#9E1A20")
        val blueInt: Int = Color.parseColor("#101077")
        val yellowInt: Int = Color.parseColor("#FFB300")
        val greenInt: Int = Color.parseColor("#205723")
        val blackInt: Int = Color.parseColor("#000000")

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
                    // will be call only when Ok button was tapped
                    //setTheme(color)

                    when (color) {
                        redInt -> {
                            setTheme(R.style.Theme_Guru)
                            setContentView(R.layout.activity_main)
                        }
                        blueInt -> {
                            setTheme(R.style.Theme_Blue)
                            setContentView(R.layout.activity_main)
                        }
                        yellowInt -> {
                            setTheme(R.style.Theme_Yellow)
                            setContentView(R.layout.activity_main)
                        }
                        greenInt -> {
                            setTheme(R.style.Theme_Green)
                            setContentView(R.layout.activity_main)
                        }
                        blackInt -> {
                            setTheme(R.style.Theme_Black)
                            setContentView(R.layout.activity_main)
                        }
                    }

                    supportActionBar!!.setBackgroundDrawable(ColorDrawable(color))

                    //supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#146775")))
                    Toast.makeText(applicationContext, "테마 변경이 완료되었습니다", Toast.LENGTH_SHORT).show()
                }

                override fun onCancel() {

                }
            }).show()
    }
}