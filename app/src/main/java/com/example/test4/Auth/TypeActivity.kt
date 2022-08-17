package com.example.test4.Auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.test4.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class TypeActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    lateinit var database : FirebaseDatabase
    lateinit var databaseReference : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_type)

        var body = findViewById<Button>(R.id.body)
        var mental = findViewById<Button>(R.id.mental)

        body.setOnClickListener {
            auth = Firebase.auth
            var key = auth.currentUser?.uid.toString()

            database = FirebaseDatabase.getInstance()
            databaseReference = database.getReference("users").child(key).child("type")

            databaseReference.setValue("신체장애")

            val intent = Intent(this, Sound_textsize_Activity::class.java)
            startActivity(intent)
        }
        mental.setOnClickListener {
            auth = Firebase.auth
            var key = auth.currentUser?.uid.toString()

            database = FirebaseDatabase.getInstance()
            databaseReference = database.getReference("users").child(key).child("type")

            databaseReference.setValue("정신장애")

            val intent = Intent(this, Sound_textsize_Activity::class.java)
            startActivity(intent)
        }
    }
}