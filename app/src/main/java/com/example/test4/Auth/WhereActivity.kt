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

class WhereActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    lateinit var database : FirebaseDatabase
    lateinit var databaseReference : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_where)

        var seoul = findViewById<Button>(R.id.seoul)
        var gyeong = findViewById<Button>(R.id.gyeong)
        var incheon = findViewById<Button>(R.id.incheon)
        var gangwon = findViewById<Button>(R.id.gangwon)
        var dejeon = findViewById<Button>(R.id.dejeon)
        var sejong = findViewById<Button>(R.id.sejong)
        var c_south = findViewById<Button>(R.id.c_south)
        var c_north = findViewById<Button>(R.id.c_north)
        var busan = findViewById<Button>(R.id.busan)
        var ulsan = findViewById<Button>(R.id.ulsan)
        var k_south = findViewById<Button>(R.id.k_south)
        var k_north = findViewById<Button>(R.id.k_north)
        var daegu = findViewById<Button>(R.id.daegu)
        var gwangju = findViewById<Button>(R.id.gwangju)
        var j_north = findViewById<Button>(R.id.j_north)
        var j_south = findViewById<Button>(R.id.j_south)
        var jeju = findViewById<Button>(R.id.jeju)


        seoul.setOnClickListener {
            auth = Firebase.auth
            var key = auth.currentUser?.uid.toString()

            database = FirebaseDatabase.getInstance()
            databaseReference = database.getReference("users").child(key).child("town")

            databaseReference.setValue("서울시")

            val intent = Intent(this, TypeActivity::class.java)
            startActivity(intent)
        }
        gyeong.setOnClickListener {
            auth = Firebase.auth
            var key = auth.currentUser?.uid.toString()

            database = FirebaseDatabase.getInstance()
            databaseReference = database.getReference("users").child(key).child("town")

            databaseReference.setValue("경기도")

            val intent = Intent(this, TypeActivity::class.java)
            startActivity(intent)
        }
        incheon.setOnClickListener {
            auth = Firebase.auth
            var key = auth.currentUser?.uid.toString()

            database = FirebaseDatabase.getInstance()
            databaseReference = database.getReference("users").child(key).child("town")

            databaseReference.setValue("인천시")

            val intent = Intent(this, TypeActivity::class.java)
            startActivity(intent)
        }
        gangwon.setOnClickListener {
            auth = Firebase.auth
            var key = auth.currentUser?.uid.toString()

            database = FirebaseDatabase.getInstance()
            databaseReference = database.getReference("users").child(key).child("town")

            databaseReference.setValue("강원도")

            val intent = Intent(this, TypeActivity::class.java)
            startActivity(intent)
        }
        dejeon.setOnClickListener {
            auth = Firebase.auth
            var key = auth.currentUser?.uid.toString()

            database = FirebaseDatabase.getInstance()
            databaseReference = database.getReference("users").child(key).child("town")

            databaseReference.setValue("대전시")

            val intent = Intent(this, TypeActivity::class.java)
            startActivity(intent)
        }
        sejong.setOnClickListener {
            auth = Firebase.auth
            var key = auth.currentUser?.uid.toString()

            database = FirebaseDatabase.getInstance()
            databaseReference = database.getReference("users").child(key).child("town")

            databaseReference.setValue("세종시")

            val intent = Intent(this, TypeActivity::class.java)
            startActivity(intent)
        }
        c_north.setOnClickListener {
            auth = Firebase.auth
            var key = auth.currentUser?.uid.toString()

            database = FirebaseDatabase.getInstance()
            databaseReference = database.getReference("users").child(key).child("town")

            databaseReference.setValue("충청북도")

            val intent = Intent(this, TypeActivity::class.java)
            startActivity(intent)
        }
        c_south.setOnClickListener {
            auth = Firebase.auth
            var key = auth.currentUser?.uid.toString()

            database = FirebaseDatabase.getInstance()
            databaseReference = database.getReference("users").child(key).child("town")

            databaseReference.setValue("충청남도")

            val intent = Intent(this, TypeActivity::class.java)
            startActivity(intent)
        }
        busan.setOnClickListener {
            auth = Firebase.auth
            var key = auth.currentUser?.uid.toString()

            database = FirebaseDatabase.getInstance()
            databaseReference = database.getReference("users").child(key).child("town")

            databaseReference.setValue("부산시")

            val intent = Intent(this, TypeActivity::class.java)
            startActivity(intent)
        }
        ulsan.setOnClickListener {
            auth = Firebase.auth
            var key = auth.currentUser?.uid.toString()

            database = FirebaseDatabase.getInstance()
            databaseReference = database.getReference("users").child(key).child("town")

            databaseReference.setValue("울산시")

            val intent = Intent(this, TypeActivity::class.java)
            startActivity(intent)
        }
        k_north.setOnClickListener {
            auth = Firebase.auth
            var key = auth.currentUser?.uid.toString()

            database = FirebaseDatabase.getInstance()
            databaseReference = database.getReference("users").child(key).child("town")

            databaseReference.setValue("경상북도")

            val intent = Intent(this, TypeActivity::class.java)
            startActivity(intent)
        }
        k_south.setOnClickListener {
            auth = Firebase.auth
            var key = auth.currentUser?.uid.toString()

            database = FirebaseDatabase.getInstance()
            databaseReference = database.getReference("users").child(key).child("town")

            databaseReference.setValue("경상남도")

            val intent = Intent(this, TypeActivity::class.java)
            startActivity(intent)
        }
        daegu.setOnClickListener {
            auth = Firebase.auth
            var key = auth.currentUser?.uid.toString()

            database = FirebaseDatabase.getInstance()
            databaseReference = database.getReference("users").child(key).child("town")

            databaseReference.setValue("대구시")

            val intent = Intent(this, TypeActivity::class.java)
            startActivity(intent)
        }
        gwangju.setOnClickListener {
            auth = Firebase.auth
            var key = auth.currentUser?.uid.toString()

            database = FirebaseDatabase.getInstance()
            databaseReference = database.getReference("users").child(key).child("town")

            databaseReference.setValue("광주시")

            val intent = Intent(this, TypeActivity::class.java)
            startActivity(intent)
        }
        j_north.setOnClickListener {
            auth = Firebase.auth
            var key = auth.currentUser?.uid.toString()

            database = FirebaseDatabase.getInstance()
            databaseReference = database.getReference("users").child(key).child("town")

            databaseReference.setValue("전라북도")

            val intent = Intent(this, TypeActivity::class.java)
            startActivity(intent)
        }
        j_south.setOnClickListener {
            auth = Firebase.auth
            var key = auth.currentUser?.uid.toString()

            database = FirebaseDatabase.getInstance()
            databaseReference = database.getReference("users").child(key).child("town")

            databaseReference.setValue("전라남도")

            val intent = Intent(this, TypeActivity::class.java)
            startActivity(intent)
        }
        jeju.setOnClickListener {
            auth = Firebase.auth
            var key = auth.currentUser?.uid.toString()

            database = FirebaseDatabase.getInstance()
            databaseReference = database.getReference("users").child(key).child("town")

            databaseReference.setValue("제주시")

            val intent = Intent(this, TypeActivity::class.java)
            startActivity(intent)
        }
    }
}