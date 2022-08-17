package com.example.test4.Auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.test4.R

class WaitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wait)

        var go_btn = findViewById<ImageView>(R.id.go_btn)

        go_btn.setOnClickListener {
            val intent = Intent(this, WhereActivity::class.java)
            startActivity(intent)
        }
    }
}