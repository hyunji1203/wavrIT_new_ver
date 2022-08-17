package com.example.test4.Auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.test4.MainActivity
import com.example.test4.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    lateinit var loginBtn1 : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginBtn1 = findViewById(R.id.loginBtn1)
        var email = findViewById<EditText>(R.id.emailArea)
        var password = findViewById<EditText>(R.id.passwordArea)

        auth = Firebase.auth

        loginBtn1.setOnClickListener {

            val email = email.text.toString()
            val password = password.text.toString()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {

                        val intent = Intent(this, MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)

                        Toast.makeText(this, "환영합니다!", Toast.LENGTH_LONG).show()

                    } else {

                        Toast.makeText(this, "아이디와 비밀번호를 다시 한 번 확인해주세요.", Toast.LENGTH_LONG).show()

                    }
                }


        }
    }
}