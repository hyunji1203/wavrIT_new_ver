package com.example.test4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class onBoard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_board)

        // onboard가 먼저 실행되고 MainActivity_home가 실행됨
        val i = Intent(this@onBoard, MainActivity::class.java)
        Handler().postDelayed({
            startActivity(i)
            finish()
        }, 1000)
    }
}