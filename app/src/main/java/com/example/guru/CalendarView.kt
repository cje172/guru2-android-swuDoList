package com.example.guru

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.CalendarView
import android.widget.ImageView
import android.widget.ListView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import java.text.SimpleDateFormat

class CalendarView : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var btnNav: ImageView
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView

    lateinit var calendarView: CalendarView
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_view)

        drawerLayout = findViewById(R.id.cal_drawer_layout)
        btnNav = findViewById(R.id.btn_nav)
        navigationView = findViewById(R.id.nav_view)

        // 네비게이션 메뉴 버튼 클릭 시 네비게이션 드로어 열기
        btnNav.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.END)
        }

        // 네비게이션 메뉴 아이템에 클릭 속성 부여
        navigationView.setNavigationItemSelectedListener(this)

        calendarView = findViewById(R.id.calendarView)
        listView = findViewById(R.id.listView)

        // 달력 최소 날짜
        calendarView.minDate = SimpleDateFormat("yyyyMMdd").parse("20220101").time
        // 달력 최대 날짜
        calendarView.maxDate = SimpleDateFormat("yyyyMMdd").parse("20221231").time
        // 선택 날짜 일정 출력
        calendarView.setOnDateChangeListener { calendarView, year, month, day ->
            // 리스트뷰에 일정 출력
        }
    }

    // 네비게이션 메뉴 아이템 클릭 시 수행
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
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

            }
            R.id.action_category -> {
                val intent = Intent(this, CategoryListView::class.java)
                startActivity(intent)
            }
            R.id.action_logout -> {

            }
        }
        // 네비게이션 뷰 닫기
        drawerLayout.closeDrawers()
        return false
    }

    // back button 클릭 시 수행 함수
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
            drawerLayout.closeDrawers()
        } else {
            super.onBackPressed()
        }
    }
}