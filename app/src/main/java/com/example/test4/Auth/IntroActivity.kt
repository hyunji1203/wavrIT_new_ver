package com.example.test4.Auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.test4.R

class IntroActivity : AppCompatActivity() {

    lateinit var LoginBtn : TextView
    lateinit var JoinBtn : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)


        LoginBtn = findViewById(R.id.LoginBtn)
        JoinBtn = findViewById(R.id.JoinBtn)

        LoginBtn.setOnClickListener {
            var intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }

        JoinBtn.setOnClickListener{
            var intent = Intent(this,JoinActivity::class.java)
            startActivity(intent)
        }
    }
}