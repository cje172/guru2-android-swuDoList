package com.example.guru

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import android.widget.Toast
import petrov.kristiyan.colorpicker.ColorPicker
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    lateinit var btn_ex: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_ex = findViewById(R.id.btn_ex)

        btn_ex.setOnClickListener {
            var intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }

    // 테마 변경 dialog
    fun openColorPicker() {
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
                        // will be call only when Ok button was tapped
                        //setTheme(color)
                        supportActionBar!!.setBackgroundDrawable(ColorDrawable(color))

                        //supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#146775")))
                        Toast.makeText(applicationContext, "테마 변경이 완료되었습니다", Toast.LENGTH_SHORT).show()
                    }

                    override fun onCancel() {

                    }
                }).show()
    }


}
