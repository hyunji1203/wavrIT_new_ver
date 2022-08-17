package com.example.test4.Auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.test4.MainActivity
import com.example.test4.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class Sound_textsize_Activity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    lateinit var database : FirebaseDatabase
    lateinit var databaseReference : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sound_textsize)

        var sizable = findViewById<Button>(R.id.sizable)
        var soundable = findViewById<Button>(R.id.soundable)

        sizable.setOnClickListener {
            auth = Firebase.auth
            var key = auth.currentUser?.uid.toString()

            database = FirebaseDatabase.getInstance()
            databaseReference = database.getReference("users").child(key).child("text")

            databaseReference.setValue("14")

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        soundable.setOnClickListener {
            auth = Firebase.auth
            var key = auth.currentUser?.uid.toString()

            database = FirebaseDatabase.getInstance()
            databaseReference = database.getReference("users").child(key).child("sound")

            databaseReference.setValue(1)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}