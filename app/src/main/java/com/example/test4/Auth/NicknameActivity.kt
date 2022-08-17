package com.example.test4.Auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.test4.MainActivity
import com.example.test4.R
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class NicknameActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    lateinit var database : FirebaseDatabase
    lateinit var databaseReference : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nickname)

        var filledTextField : TextInputLayout = findViewById(R.id.filledTextField)
        var go_Btn2 = findViewById<ImageView>(R.id.go_btn2)

        go_Btn2.setOnClickListener {
            // Get input text
            val inputText = filledTextField.editText?.text.toString()

            auth = Firebase.auth
            var key = auth.currentUser?.uid.toString()

            database = FirebaseDatabase.getInstance()
            databaseReference = database.getReference("users").child(key).child("nickname")

            databaseReference.setValue(inputText)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}